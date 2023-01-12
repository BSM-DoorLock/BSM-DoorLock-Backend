package bssm.doorlock.domain.room.domain.repository;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.room.domain.RoomAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomAccessLogRepository extends JpaRepository<RoomAccessLog, Long> {

    List<RoomAccessLog> findAllByRoomOrderByIdDesc(Room room);
}
