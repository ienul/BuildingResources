package com.ienulescu.brat.mapper;

import com.ienulescu.brat.dto.DoorDto;
import com.ienulescu.brat.model.Door;
import org.springframework.stereotype.Service;

@Service
public class DoorMapper {

    public Door map(DoorDto doorDto){

        Door door = new Door();
        door.setDoorId(doorDto.getDoorId());
        door.setRoomOneId(doorDto.getRoomOneId());
        door.setRoomTwoId(doorDto.getRoomTwoId());
        door.setName(doorDto.getName());
        door.setExterior(doorDto.getExterior());
        return door;

    }


    public DoorDto map(Door door){

        DoorDto doorDto = new DoorDto();
        doorDto.setDoorId(door.getDoorId());
        doorDto.setRoomOneId(door.getRoomOneId());
        doorDto.setRoomTwoId(door.getRoomTwoId());
        doorDto.setName(door.getName());
        doorDto.setExterior(door.getExterior());

        return doorDto;

    }

}
