package bssm.doorlock.domain.room.domain.repository;

import bssm.doorlock.domain.room.domain.RoomGuest;
import bssm.doorlock.domain.room.domain.RoomGuestPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomGuestRepository extends JpaRepository<RoomGuest, RoomGuestPk> {

}
