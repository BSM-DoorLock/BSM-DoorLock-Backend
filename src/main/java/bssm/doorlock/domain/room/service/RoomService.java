package bssm.doorlock.domain.room.service;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.room.facade.RoomFacade;
import bssm.doorlock.domain.room.presentation.dto.res.RoomRes;
import bssm.doorlock.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomFacade roomFacade;

    public RoomRes getMyRoom(User user) {
        return roomFacade.getMyRoom(user)
                .toResponse();
    }

    public RoomRes getRoomById(Long id) {
        return roomFacade.getRoomById(id)
                .toResponse();
    }

    public List<RoomRes> getAllRoomList() {
        return roomFacade.getAllRoomList().stream()
                .map(Room::toResponse)
                .toList();
    }

}
