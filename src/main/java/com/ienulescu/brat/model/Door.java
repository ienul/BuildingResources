package com.ienulescu.brat.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Door {

    @Id
    @GeneratedValue
    private Integer id;

    private String doorId;
    private String roomOneId;
    private String roomTwoId;
    private String name;
    private Boolean exterior;
}
