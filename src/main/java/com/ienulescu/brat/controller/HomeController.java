package com.ienulescu.brat.controller;

import com.ienulescu.brat.dto.BuildingDto;
import com.ienulescu.brat.dto.DoorDto;
import com.ienulescu.brat.dto.PaginationDto;
import com.ienulescu.brat.dto.RoomDto;
import com.ienulescu.brat.service.ResourceService;
import com.ienulescu.brat.validator.BuildingDtoValidator;
import com.ienulescu.brat.validator.DoorDtoValidator;
import com.ienulescu.brat.validator.RoomDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private BuildingDtoValidator buildingDtoValidator;
    @Autowired
    private RoomDtoValidator roomDtoValidator;
    @Autowired
    private DoorDtoValidator doorDtoValidator;


    @GetMapping(value = "/home")
    public String getHomePage(Model model) {
        BuildingDto buildingDto = new BuildingDto();
        model.addAttribute("buildingDto", buildingDto);

        RoomDto roomDto = resourceService.createDefaultRoomDto();
        model.addAttribute("roomDto", roomDto);

        DoorDto doorDto = resourceService.createDefaultDoorDto();
        model.addAttribute("doorDto", doorDto);

        model.addAttribute("addBuilding", true);
        model.addAttribute("addRoom", false);
        model.addAttribute("addDoor", false);
        return "home";
    }

    @PostMapping(value = "/home", params="action=addBuilding")
    public String postHomePage(Model model, @ModelAttribute("buildingDto") BuildingDto buildingDto, BindingResult bindingResult,
                               @ModelAttribute("roomDto") RoomDto roomDto,
                               @ModelAttribute("doorDto") DoorDto doorDto) {
        model.addAttribute("addBuilding", true);
        buildingDtoValidator.validate(buildingDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "home";
        }
        resourceService.addBuilding(buildingDto);
        return "redirect:/home";
    }

    @PostMapping(value = "/home", params="action=addRoom")
    public String postHomePage(Model model, @ModelAttribute("roomDto") RoomDto roomDto, BindingResult bindingResult,
                               @ModelAttribute("buildingDto") BuildingDto buildingDto,
                               @ModelAttribute("doorDto") DoorDto doorDto) {
        System.out.println(roomDto);
        model.addAttribute("addRoom", true);
        roomDtoValidator.validate(roomDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "home";
        }
        resourceService.addRoom(roomDto);
        return "redirect:/home";
    }

    @PostMapping(value = "/home", params="action=addDoor")
    public String postHomePage(Model model,@ModelAttribute("doorDto") DoorDto doorDto, BindingResult bindingResult,
                               @ModelAttribute("buildingDto") BuildingDto buildingDto,
                               @ModelAttribute("roomDto") RoomDto roomDto) {
        System.out.println(doorDto);
        model.addAttribute("addDoor", true);
        doorDtoValidator.validate(doorDto, bindingResult);
        if (bindingResult.hasErrors()){
            return "home";
        }
        resourceService.addDoor(doorDto);
        return "redirect:/home";
    }

    @GetMapping(value = "/viewResources")
    public String getViewResourcesBuildings(Model model, @RequestParam(required = false) String buildingsPage,
                                            @RequestParam(required = false) String roomsPage,
                                            @RequestParam(required = false) String doorsPage){
        System.out.println("S-a apelat Get pe viewResources!");
        System.out.println("buildingsPage este " + buildingsPage);
        PaginationDto pagination = new PaginationDto(buildingsPage, roomsPage, doorsPage);
        model.addAttribute("pagination", pagination);

        List<BuildingDto> buildings = resourceService.findAllBuildings(pagination);
        model.addAttribute("buildings", buildings);

        List<RoomDto> rooms = resourceService.findAllRooms(pagination);
        model.addAttribute("rooms", rooms);

        List<DoorDto> doors = resourceService.findAllDoors(pagination);
        model.addAttribute("doors", doors);

        return "viewResources";
    }

    @GetMapping(value = "/buildings/{buildingId}")
    public String getBuildingEditPage(Model model, @PathVariable(value = "buildingId") String buildingId){

        System.out.println("S-a apelat EDIT! Cu valoarea building ID "+ buildingId);
        BuildingDto buildingDto = resourceService.getBuildingById(buildingId);
        model.addAttribute("buildingDto", buildingDto);
        return "editBuilding";
    }

    @PostMapping(value = "/buildings/{buildingId}/delete")
    public String postDeleteBuilding(@PathVariable(value = "buildingId") String buildingId){

        System.out.println("S-a apelat DELETE! Cu valoarea building ID "+ buildingId );
        resourceService.deleteBuilding(buildingId);
        return "redirect:/viewResources";
    }

    @PostMapping(value = "/buildings/{buildingId}/update")
    public String postUpdateBuilding(@PathVariable(value = "buildingId") String buildingId, @ModelAttribute("buildingDto") BuildingDto buildingDto){

        System.out.println("S-a apelat UPDATE! Cu valoarea buildingDto "+ buildingDto );
        resourceService.updateBuilding(buildingId,buildingDto);
        return "redirect:/viewResources";
    }

    @GetMapping(value = "/rooms/{roomId}")
    public String getRoomEditPage(Model model, @PathVariable(value = "roomId") String roomId){

        System.out.println("S-a apelat EDIT! Cu valoarea room ID "+ roomId);
        RoomDto roomDto = resourceService.getRoomById(roomId);
        model.addAttribute("roomDto", roomDto);
        return "editRoom";
    }

    @PostMapping(value = "/rooms/{roomId}/delete")
    public String postDeleteRoom(@PathVariable(value = "roomId") String roomId){

        System.out.println("S-a apelat DELETE! Cu valoarea room ID "+ roomId );
        resourceService.deleteRoom(roomId);
        return "redirect:/viewResources";
    }

    @PostMapping(value = "/rooms/{roomId}/update")
    public String postUpdateRoom(@PathVariable(value = "roomId") String roomId, @ModelAttribute("roomDto") RoomDto roomDto){

        System.out.println("S-a apelat UPDATE! Cu valoarea roomDto "+ roomDto );
        resourceService.updateRoom(roomId,roomDto);
        return "redirect:/viewResources";
    }

    @GetMapping(value = "/doors/{doorId}")
    public String getDoorEditPage(Model model, @PathVariable(value = "doorId") String doorId){

        System.out.println("S-a apelat EDIT! Cu valoarea door ID "+ doorId);
        DoorDto doorDto = resourceService.getDoorById(doorId);
        model.addAttribute("doorDto", doorDto);
        return "editDoor";
    }

    @PostMapping(value = "/doors/{doorId}/delete")
    public String postDeleteDoor(@PathVariable(value = "doorId") String doorId){

        System.out.println("S-a apelat DELETE! Cu valoarea door ID "+ doorId );
        resourceService.deleteDoor(doorId);
        return "redirect:/viewResources";
    }

    @PostMapping(value = "/doors/{doorId}/update")
    public String postUpdateDoor(@PathVariable(value = "doorId") String doorId, @ModelAttribute("doorDto") DoorDto doorDto){

        System.out.println("S-a apelat UPDATE! Cu valoarea doorDto "+ doorDto );
        resourceService.updateDoor(doorId,doorDto);
        return "redirect:/viewResources";
    }


}