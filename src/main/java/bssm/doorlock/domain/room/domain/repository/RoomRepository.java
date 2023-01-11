package bssm.doorlock.domain.room.domain.repository;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByOwners(User user);

}
