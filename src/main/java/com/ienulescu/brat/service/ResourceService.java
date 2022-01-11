package com.ienulescu.brat.service;

import com.ienulescu.brat.dto.BuildingDto;
import com.ienulescu.brat.dto.DoorDto;
import com.ienulescu.brat.dto.PaginationDto;
import com.ienulescu.brat.dto.RoomDto;
import com.ienulescu.brat.mapper.BuildingMapper;
import com.ienulescu.brat.mapper.DoorMapper;
import com.ienulescu.brat.mapper.RoomMapper;
import com.ienulescu.brat.model.Building;
import com.ienulescu.brat.model.Door;
import com.ienulescu.brat.model.Room;
import com.ienulescu.brat.repository.BuildingRepository;
import com.ienulescu.brat.repository.DoorRepository;
import com.ienulescu.brat.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResourceService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private DoorRepository doorRepository;
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private DoorMapper doorMapper;

    public void addBuilding(BuildingDto buildingDto) {

        Building building = buildingMapper.map(buildingDto);
        buildingRepository.save(building);
    }

    public void addRoom(RoomDto roomDto) {
        Room room = roomMapper.map(roomDto);
        roomRepository.save(room);
    }

    public void addDoor(DoorDto doorDto) {
        Door door = doorMapper.map(doorDto);
        doorRepository.save(door);
    }

    public RoomDto createDefaultRoomDto() {
        List<Building> allBuildings = buildingRepository.findAll();
        List<String> buildingIds = new ArrayList<>();
        for (Building building : allBuildings) {
            buildingIds.add(building.getBuildingId());
        }
        RoomDto roomDto = new RoomDto();
        roomDto.setBuildingIds(buildingIds);
        return roomDto;
    }

    public DoorDto createDefaultDoorDto() {
        List<Room> allRooms = roomRepository.findAll();
        List<String> roomIds = new ArrayList<>();
        for (Room room : allRooms) {
            roomIds.add(room.getRoomId());
        }
        DoorDto doorDto = new DoorDto();
        doorDto.setRoomIds(roomIds);
        return doorDto;
    }

    public List<BuildingDto> findAllBuildings(PaginationDto paginationDto) {

        Pageable pageable = PageRequest.of(paginationDto.getBuildingsPage() - 1, paginationDto.getSizeOfPage());

        Page<Building> all = buildingRepository.findAll(pageable);
        List<Building> buildingsList = all.getContent();
        List<BuildingDto> buildingsDtos = new ArrayList<>();
        for (Building building : buildingsList) {
            BuildingDto buildingDto = buildingMapper.map(building);
            buildingsDtos.add(buildingDto);
        }
        return buildingsDtos;
    }

    public List<RoomDto> findAllRooms(PaginationDto paginationDto) {

        Pageable pageable = PageRequest.of(paginationDto.getRoomsPage() - 1, paginationDto.getSizeOfPage());

        Page<Room> all = roomRepository.findAll(pageable);
        List<Room> roomsList = all.getContent();
        List<RoomDto> roomsDtos = new ArrayList<>();
        for (Room room : roomsList) {
            RoomDto roomDto = roomMapper.map(room);
            roomsDtos.add(roomDto);
        }
        return roomsDtos;
    }

    public List<DoorDto> findAllDoors(PaginationDto paginationDto) {

        Pageable pageable = PageRequest.of(paginationDto.getDoorsPage() - 1, paginationDto.getSizeOfPage());

        Page<Door> all = doorRepository.findAll(pageable);
        List<Door> doorsList = all.getContent();
        List<DoorDto> doorsDtos = new ArrayList<>();
        for (Door door : doorsList) {
            DoorDto doorDto = doorMapper.map(door);
            doorsDtos.add(doorDto);
        }
        return doorsDtos;
    }


    public void deleteBuilding(String buildingId) {
        buildingRepository.deleteByBuildingId(buildingId);
    }

    public BuildingDto getBuildingById(String buildingId) {
        Optional<Building> optionalBuilding = buildingRepository.findByBuildingId(buildingId);
        BuildingDto buildingDto = buildingMapper.map(optionalBuilding.get());
        return buildingDto;
    }

    public void updateBuilding(String buildingId, BuildingDto buildingDto) {
        Optional<Building> optionalBuilding = buildingRepository.findByBuildingId(buildingId);
        if (optionalBuilding.isEmpty()) {
            return;
        }
        Building building = optionalBuilding.get();
        building.setName(buildingDto.getName());
        building.setFloorNumber(Integer.parseInt(buildingDto.getFloorNumber()));
        building.setDescription(buildingDto.getDescription());
        building.setBuildingId(buildingDto.getBuildingId());
        buildingRepository.save(building);
    }

    public void deleteRoom(String roomId) {
        roomRepository.deleteByRoomId(roomId);
    }

    public RoomDto getRoomById(String roomId) {
        Optional<Room> optionalRoom = roomRepository.findByRoomId(roomId);
        RoomDto roomDto = roomMapper.map(optionalRoom.get());
        List<Building> buildings = buildingRepository.findAll();
        List<String> buildingIds = new ArrayList<>();
        for(Building building:buildings){
            buildingIds.add(building.getBuildingId());
        }
        System.out.println(buildingIds);
        roomDto.setBuildingIds(buildingIds);
        return roomDto;
    }

    public void updateRoom(String roomId, RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findByRoomId(roomId);
        if (optionalRoom.isEmpty()) {
            return;
        }
        Room room = optionalRoom.get();
        room.setName(roomDto.getName());
        room.setBuildingId(roomDto.getBuildingId());
        room.setSquareMeters(Integer.parseInt(roomDto.getSquareMeters()));
        room.setRoomId(roomDto.getRoomId());
        roomRepository.save(room);
    }


    public void deleteDoor(String doorId) {
        doorRepository.deleteByDoorId(doorId);
    }

    public DoorDto getDoorById(String doorId) {
        Optional<Door> optionalDoor = doorRepository.findByDoorId(doorId);
        List<Room> rooms = roomRepository.findAll();
        List<String> roomIds = new ArrayList<>();
        for (Room room:rooms) {
            roomIds.add(room.getRoomId());
        }
        DoorDto doorDto = doorMapper.map(optionalDoor.get());
        doorDto.setRoomIds(roomIds);
        return doorDto;
    }

    public void updateDoor(String doorId, DoorDto doorDto) {
        Optional<Door> optionalDoor = doorRepository.findByDoorId(doorId);
        if (optionalDoor.isEmpty()) {
            return;
        }
        Door door = optionalDoor.get();
        door.setDoorId(doorDto.getDoorId());
        door.setRoomOneId(doorDto.getRoomOneId());
        door.setRoomTwoId(doorDto.getRoomTwoId());
        door.setName(doorDto.getName());
        door.setExterior(doorDto.getExterior());
        doorRepository.save(door);
    }
}