package bssm.doorlock.domain.room.service;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.room.domain.RoomShare;
import bssm.doorlock.domain.room.domain.RoomShareStat;
import bssm.doorlock.domain.room.facade.RoomFacade;
import bssm.doorlock.domain.room.facade.RoomShareFacade;
import bssm.doorlock.domain.room.presentation.dto.req.AskRoomShareReq;
import bssm.doorlock.domain.room.presentation.dto.res.RoomRes;
import bssm.doorlock.domain.room.presentation.dto.res.RoomShareRes;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomFacade roomFacade;
    private final RoomShareFacade roomShareFacade;
    private final UserFacade userFacade;

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

    public void askRoomShare(User user, AskRoomShareReq req) {
        User owner = userFacade.getUserByStudentId(req.getOwnerStudentId());
        Room room = roomFacade.getRoomByOwner(owner);

        RoomShare roomShare = RoomShare.builder()
                .guest(user)
                .owner(owner)
                .room(room)
                .stat(RoomShareStat.WAITING)
                .build();

        roomShareFacade.save(roomShare);
    }

}
