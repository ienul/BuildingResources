package com.ienulescu.brat.repository;


import com.ienulescu.brat.model.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DoorRepository extends JpaRepository<Door,Integer> {

    Optional<Door> findByDoorId(String doorId);

    @Transactional
    void deleteByDoorId(String doorId);
}
