package bssm.doorlock.domain.room.facade;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.room.domain.repository.RoomRepository;
import bssm.doorlock.domain.room.exception.RoomNotFoundException;
import bssm.doorlock.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomFacade {

    private final RoomRepository roomRepository;

    public Room getMyRoom(User user) {
        return roomRepository.findByOwners(user)
                .orElseThrow(RoomNotFoundException::new);
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);
    }

    public List<Room> getAllRoomList() {
        return roomRepository.findAll();
    }

}
