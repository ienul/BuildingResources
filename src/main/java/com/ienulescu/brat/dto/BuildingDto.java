package com.ienulescu.brat.dto;

import lombok.Data;

@Data
public class BuildingDto { //un obiect de tipul acesta va fi pus pe un formular

    private String buildingId; //in formularul care are obiectul mentionat, vom avea un input care va avea valoarea acestui field
    private String name;
    private String description;
    private String floorNumber;

}
