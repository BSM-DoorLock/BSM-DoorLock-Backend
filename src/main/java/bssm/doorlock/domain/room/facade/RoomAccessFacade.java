package bssm.doorlock.domain.room.facade;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.room.domain.RoomAccessLog;
import bssm.doorlock.domain.room.domain.RoomAccessStat;
import bssm.doorlock.domain.room.domain.repository.RoomAccessLogRepository;
import bssm.doorlock.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomAccessFacade {

    private final RoomAccessLogRepository roomAccessLogRepository;

    public void saveLog(Room room, User user, RoomAccessStat accessStat) {
        RoomAccessLog newLog = RoomAccessLog.builder()
                .room(room)
                .user(user)
                .accessStat(accessStat)
                .build();
        roomAccessLogRepository.save(newLog);
    }

    public List<RoomAccessLog> getAllByRoom(Room room) {
        return roomAccessLogRepository.findAllByRoomOrderByIdDesc(room);
    }

}
