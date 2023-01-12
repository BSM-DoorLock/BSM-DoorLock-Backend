package bssm.doorlock.domain.room.service;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.room.facade.RoomFacade;
import bssm.doorlock.domain.room.presentation.dto.res.RoomRankingRes;
import bssm.doorlock.domain.room.presentation.dto.res.RoomStudentRankingRes;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomRankingService {

    private final RoomFacade roomFacade;
    private final UserFacade userFacade;

    public List<RoomRankingRes> orderBySharedRoom() {
        return roomFacade.getAllRoomList().stream()
                .map(Room::toRankingResponse)
                .sorted()
                .toList();
    }

    public List<RoomStudentRankingRes> orderBySharedStudent() {
        return userFacade.getAll().stream()
                .map(User::toRankingResponse)
                .sorted()
                .toList();
    }

}
