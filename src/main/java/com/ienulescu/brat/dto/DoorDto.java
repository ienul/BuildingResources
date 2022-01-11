package com.ienulescu.brat.dto;

import lombok.Data;

import java.util.List;

@Data
public class DoorDto {

    private String doorId;
    private String roomOneId;
    private String roomTwoId;
    private String name;
    private Boolean exterior;
    private List<String> roomIds;
}