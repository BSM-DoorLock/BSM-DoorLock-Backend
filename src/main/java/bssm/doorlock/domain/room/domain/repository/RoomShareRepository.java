package bssm.doorlock.domain.room.domain.repository;

import bssm.doorlock.domain.room.domain.RoomShare;
import bssm.doorlock.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomShareRepository extends JpaRepository<RoomShare, Long> {

    List<RoomShare> findAllByGuest(User user);

    List<RoomShare> findAllByOwner(User user);

}
