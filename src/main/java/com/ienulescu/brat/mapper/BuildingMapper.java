package com.ienulescu.brat.mapper;

import com.ienulescu.brat.dto.BuildingDto;
import com.ienulescu.brat.model.Building;
import org.springframework.stereotype.Service;

@Service
public class BuildingMapper {

    public Building map(BuildingDto buildingDto) {

        Building building = new Building();
        building.setBuildingId(buildingDto.getBuildingId());
        building.setDescription(buildingDto.getDescription());
        building.setFloorNumber(Integer.valueOf(buildingDto.getFloorNumber()));
        building.setName(buildingDto.getName());
        return building;
    }

    public BuildingDto map(Building building){
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setBuildingId(building.getBuildingId());
        buildingDto.setDescription(building.getDescription());
        buildingDto.setFloorNumber(building.getFloorNumber().toString());
        buildingDto.setName(building.getName());
        return buildingDto;
    }

}