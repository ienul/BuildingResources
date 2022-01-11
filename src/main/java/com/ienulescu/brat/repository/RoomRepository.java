package com.ienulescu.brat.repository;

import com.ienulescu.brat.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    Optional<Room> findByRoomId (String roomId);

    @Transactional
    void deleteByRoomId(String roomId);
}
