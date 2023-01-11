package bssm.doorlock.domain.room.domain.repository;

import bssm.doorlock.domain.room.domain.RoomGuest;
import bssm.doorlock.domain.room.domain.RoomGuestPk;
import bssm.doorlock.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomGuestRepository extends JpaRepository<RoomGuest, RoomGuestPk> {

    List<RoomGuest> findAllByUser(User guest);
}
