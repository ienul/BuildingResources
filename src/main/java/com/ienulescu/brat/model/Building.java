package com.ienulescu.brat.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Building {

    @Id
    @GeneratedValue
    private Integer id;
    private String buildingId;
    private String name;
    private String description;
    private Integer floorNumber;
}
