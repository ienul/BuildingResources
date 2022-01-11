package com.ienulescu.brat.validator;

import com.ienulescu.brat.dto.RoomDto;
import com.ienulescu.brat.model.Building;
import com.ienulescu.brat.model.Room;
import com.ienulescu.brat.repository.BuildingRepository;
import com.ienulescu.brat.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Service
public class RoomDtoValidator {

    @Autowired
    private BuildingRepository buildingRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    public void validate(RoomDto roomDto, BindingResult bindingResult){

        if (roomDto.getRoomId().length() == 0 ){
            FieldError fieldError = new FieldError("roomDto", "roomId", "Room ID cannot be empty!");
            bindingResult.addError(fieldError);
        }
        if (roomDto.getBuildingId().length() == 0 ){
            FieldError fieldError = new FieldError("roomDto", "buildingId", "Building ID cannot be empty!");
            bindingResult.addError(fieldError);
        }
        if (roomDto.getName().length() == 0 ){
            FieldError fieldError = new FieldError("roomDto", "name", "Building name cannot be empty!");
            bindingResult.addError(fieldError);
        }
        if (roomDto.getSquareMeters().length() == 0 ){
            FieldError fieldError = new FieldError("roomDto", "squareMeters", "Area cannot be empty!");
            bindingResult.addError(fieldError);
        }else{
            try {
                int squareMeters = Integer.parseInt(roomDto.getSquareMeters());
                if (squareMeters <=0){
                    FieldError fieldError = new FieldError("roomDto", "squareMeters", "Area should be a positive number!");
                    bindingResult.addError(fieldError);
                }
            }catch (Exception e){
                FieldError fieldError = new FieldError("roomDto", "squareMeters", "Area should be a number!");
                bindingResult.addError(fieldError);
            }
        }

        Optional<Room> optionalRoom = roomRepository.findByRoomId(roomDto.getRoomId());
        if (optionalRoom.isPresent()){
            FieldError fieldError = new FieldError("roomDto", "roomId", "Room ID should be unique!");
            bindingResult.addError(fieldError);
        }
        Optional<Building> optionalBuilding = buildingRepository.findByBuildingId(roomDto.getBuildingId());
        if (optionalBuilding.isEmpty()){
            FieldError fieldError = new FieldError("roomDto", "buildingId", "Building should exist!");
            bindingResult.addError(fieldError);
        }


    }

}


