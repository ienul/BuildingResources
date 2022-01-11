package com.ienulescu.brat.validator;

import com.ienulescu.brat.dto.BuildingDto;
import com.ienulescu.brat.model.Building;
import com.ienulescu.brat.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Service
public class BuildingDtoValidator {

    @Autowired
    private BuildingRepository buildingRepository;

    public void validate(BuildingDto buildingDto, BindingResult bindingResult){

        if (buildingDto.getBuildingId().length() == 0 ){
            FieldError fieldError = new FieldError("buildingDto", "buildingId", "Building ID cannot be empty!");
            bindingResult.addError(fieldError);
        }

        if (buildingDto.getName().length() == 0 ){
            FieldError fieldError = new FieldError("buildingDto", "name", "Building name cannot be empty!");
            bindingResult.addError(fieldError);
        }

        if (buildingDto.getDescription().length() < 5 ){
            FieldError fieldError = new FieldError("buildingDto", "description", "Building description must be at least five characters!");
            bindingResult.addError(fieldError);
        }

        if (buildingDto.getFloorNumber().length() == 0 ){
            FieldError fieldError = new FieldError("buildingDto", "floorNumber", "Floor number must be specified!");
            bindingResult.addError(fieldError);
        }else{

            try {
                Integer.parseInt(buildingDto.getFloorNumber());
            } catch(Exception exception) {
                FieldError fieldError = new FieldError("buildingDto", "floorNumber", "Floor number must be specified as a number!");
                bindingResult.addError(fieldError);
            }
        }

        String buildingId = buildingDto.getBuildingId();
        Optional<Building> optionalBuilding = buildingRepository.findByBuildingId(buildingId);

        if (optionalBuilding.isPresent()) {
            FieldError fieldError = new FieldError("buildingDto", "buildingId", "Building ID must be unique!");
            bindingResult.addError(fieldError);
        }

    }

}