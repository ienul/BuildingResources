package com.ienulescu.brat.repository;


import com.ienulescu.brat.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building,Integer> {



   Optional<Building> findByBuildingId(String buildingId);

   @Transactional
   void deleteByBuildingId(String buildingId);

}