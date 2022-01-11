package com.ienulescu.brat.validator;

import com.ienulescu.brat.dto.DoorDto;
import com.ienulescu.brat.model.Door;
import com.ienulescu.brat.model.Room;
import com.ienulescu.brat.repository.DoorRepository;
import com.ienulescu.brat.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Service
public class DoorDtoValidator {

    @Autowired
    private DoorRepository doorRepository;
    @Autowired
    private RoomRepository roomRepository;

    public void validate(DoorDto doorDto, BindingResult bindingResult) {

        if (doorDto.getDoorId().length() == 0 ){
            FieldError fieldError = new FieldError("doorDto", "doorId", "Door ID cannot be empty!");
            bindingResult.addError(fieldError);
        }
        if (doorDto.getRoomOneId().length() == 0 ){
            FieldError fieldError = new FieldError("doorDto", "roomOneId", "Room One ID cannot be empty!");
            bindingResult.addError(fieldError);
        }

        if (doorDto.getName().length() == 0 ){
            FieldError fieldError = new FieldError("doorDto", "name", "Door Name cannot be empty!");
            bindingResult.addError(fieldError);
        }

        Optional<Door> optionalDoor = doorRepository.findByDoorId(doorDto.getDoorId());
        if (optionalDoor.isPresent()){
            FieldError fieldError = new FieldError("doorDto", "doorId", "Door ID must be unique!");
            bindingResult.addError(fieldError);
        }

        Optional<Room> optionalRoomOne = roomRepository.findByRoomId(doorDto.getRoomOneId());
        if (optionalRoomOne.isEmpty()){
            FieldError fieldError = new FieldError("doorDto", "roomOneId", "Room one should exist!");
            bindingResult.addError(fieldError);
        }

        if (doorDto.getExterior()){ // e usa de exterior, TREBUIE sa aiba un singur ID.
            if (doorDto.getRoomTwoId().length() > 0 ){
                FieldError fieldError = new FieldError("doorDto", "roomTwoId", "Room Two ID must be empty!");
                bindingResult.addError(fieldError);
            }
            return;
        }
     // este usa de interior, TREBUIE sa aiba doua ID-uri
        if (doorDto.getRoomTwoId().length() == 0 ){
            FieldError fieldError = new FieldError("doorDto", "roomTwoId", "Room Two ID cannot be empty!");
            bindingResult.addError(fieldError);
            return;
        }

        Optional<Room> optionalRoomTwo = roomRepository.findByRoomId(doorDto.getRoomTwoId());
        if (optionalRoomTwo.isEmpty()){
            FieldError fieldError = new FieldError("doorDto", "roomTwoId", "Room two should exist!");
            bindingResult.addError(fieldError);
            return;
        }
        if (doorDto.getRoomTwoId().equalsIgnoreCase(doorDto.getRoomOneId())){
            FieldError fieldError = new FieldError("doorDto", "roomTwoId", "Rooms cannot have same ID for interior doors!");
            bindingResult.addError(fieldError);
        }
    }
}