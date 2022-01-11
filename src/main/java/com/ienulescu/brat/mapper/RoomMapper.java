package com.ienulescu.brat.mapper;

import com.ienulescu.brat.dto.RoomDto;
import com.ienulescu.brat.model.Room;
import org.springframework.stereotype.Service;

@Service
public class RoomMapper {

    public Room map(RoomDto roomDto){

        Room room = new Room();
        room.setRoomId(roomDto.getRoomId());
        room.setBuildingId(roomDto.getBuildingId());
        room.setName(roomDto.getName());
        room.setSquareMeters(Integer.valueOf(roomDto.getSquareMeters()));
        return room;

    }

    public RoomDto map(Room room){
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomId(room.getRoomId());
        roomDto.setBuildingId(room.getBuildingId());
        roomDto.setName(room.getName());
        roomDto.setSquareMeters(room.getSquareMeters().toString());
        return roomDto;
    }



}
