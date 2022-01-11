package com.ienulescu.brat.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomDto {

    private String roomId;
    private String buildingId;
    private String name;
    private String squareMeters;
    private List<String> buildingIds;

}