package com.ienulescu.brat.dto;

import lombok.Data;

@Data
public class PaginationDto {

    private int buildingsPage;
    private int roomsPage;
    private int doorsPage;

    private int sizeOfPage;

    public PaginationDto(String buildingsPage, String roomsPage, String doorsPage){
        this.buildingsPage = pageNumberFrom(buildingsPage);
        this.roomsPage = pageNumberFrom(roomsPage);
        this.doorsPage = pageNumberFrom(doorsPage);
        sizeOfPage = 5;
    }

    private int pageNumberFrom(String someParameter) {
        try{
            int parameterAsNumber = Integer.parseInt(someParameter);
            if (parameterAsNumber > 0){
                return parameterAsNumber;
            }
        }catch (NumberFormatException exception){
            //ignoring exception because we will return default value after try/catch
        }
        return 1;
    }
}