package bssm.doorlock.domain.room.service;

import bssm.doorlock.domain.room.facade.RoomFacade;
import bssm.doorlock.domain.room.presentation.dto.res.RoomRes;
import bssm.doorlock.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomFacade roomFacade;

    public RoomRes getMyRoom(User user) {
        return roomFacade.getMyRoom(user)
                .toResponse();
    }
}
