package bssm.doorlock.domain.room.domain.repository;

import bssm.doorlock.domain.room.domain.Room;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @EntityGraph(attributePaths = {"owners", "owners.student", "owners.student.user", "guests", "guests.user"})
    List<Room> findAll();
}
