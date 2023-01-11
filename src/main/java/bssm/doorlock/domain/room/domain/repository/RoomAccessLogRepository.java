package bssm.doorlock.domain.room.domain.repository;

import bssm.doorlock.domain.room.domain.RoomAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomAccessLogRepository extends JpaRepository<RoomAccessLog, Long> {
}
