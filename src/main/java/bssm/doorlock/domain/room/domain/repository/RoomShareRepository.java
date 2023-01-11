package bssm.doorlock.domain.room.domain.repository;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.room.domain.RoomShare;
import bssm.doorlock.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomShareRepository extends JpaRepository<RoomShare, Long> {

    List<Room> findAllByGuest(User user);

    List<Room> findAllByOwner(User user);

}
