package bssm.doorlock.domain.room.domain.repository;

import bssm.doorlock.domain.room.domain.RoomOwner;
import bssm.doorlock.domain.room.domain.RoomOwnerPk;
import bssm.doorlock.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomOwnerRepository extends JpaRepository<RoomOwner, RoomOwnerPk> {

    Optional<RoomOwner> findByUser(User owner);
}
