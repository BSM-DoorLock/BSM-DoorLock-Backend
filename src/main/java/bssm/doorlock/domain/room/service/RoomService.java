package bssm.doorlock.domain.room.service;

import bssm.doorlock.domain.room.domain.*;
import bssm.doorlock.domain.room.facade.RoomFacade;
import bssm.doorlock.domain.room.facade.RoomGuestFacade;
import bssm.doorlock.domain.room.facade.RoomShareFacade;
import bssm.doorlock.domain.room.presentation.dto.req.AcceptRoomShareReq;
import bssm.doorlock.domain.room.presentation.dto.req.AskRoomShareReq;
import bssm.doorlock.domain.room.presentation.dto.res.RoomRes;
import bssm.doorlock.domain.room.presentation.dto.res.RoomShareRes;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomFacade roomFacade;
    private final RoomShareFacade roomShareFacade;
    private final RoomGuestFacade roomGuestFacade;
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

    @Transactional
    public void acceptRoomShare(User user, AcceptRoomShareReq req) {
        RoomShare roomShare = roomShareFacade.getById(req.getShareId());
        roomShare.setStat(req.getStat());

        RoomGuest roomGuest = RoomGuest.builder()
                .pk(RoomGuestPk.builder()
                        .roomId(roomShare.getRoom().getId())
                        .userCode(user.getCode())
                        .build())
                .build();

        roomGuestFacade.save(roomGuest);
    }

    public List<RoomShareRes> getAskShareList(User guest) {
        return roomShareFacade.getAskShareList(guest).stream()
                .map(RoomShare::toResponse)
                .toList();
    }

    public List<RoomShareRes> getReceiveShareList(User owner) {
        return roomShareFacade.getReceiveShareList(owner).stream()
                .map(RoomShare::toResponse)
                .toList();
    }

}
