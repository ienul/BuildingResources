package com.ienulescu.brat.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue
    private Integer id;

    private String roomId;
    private String buildingId;
    private String name;
    private Integer squareMeters;
}
