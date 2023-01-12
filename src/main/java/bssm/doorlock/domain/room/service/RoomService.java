package bssm.doorlock.domain.room.service;

import bssm.doorlock.domain.room.domain.*;
import bssm.doorlock.domain.room.exception.ForbiddenAccessRoomException;
import bssm.doorlock.domain.room.facade.*;
import bssm.doorlock.domain.room.presentation.dto.req.AcceptRoomShareReq;
import bssm.doorlock.domain.room.presentation.dto.req.AskRoomShareReq;
import bssm.doorlock.domain.room.presentation.dto.req.UpdateDoorStateReq;
import bssm.doorlock.domain.room.presentation.dto.res.RoomAccessLogRes;
import bssm.doorlock.domain.room.presentation.dto.res.RoomAccessNotificationRes;
import bssm.doorlock.domain.room.presentation.dto.res.RoomRes;
import bssm.doorlock.domain.room.presentation.dto.res.RoomShareRes;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.facade.UserFacade;
import bssm.doorlock.global.socket.SocketUtil;
import bssm.doorlock.global.socket.domain.SocketEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomFacade roomFacade;
    private final RoomShareFacade roomShareFacade;
    private final RoomOwnerFacade roomOwnerFacade;
    private final RoomGuestFacade roomGuestFacade;
    private final RoomAccessFacade roomAccessFacade;
    private final RoomNotificationFacade roomNotificationFacade;
    private final UserFacade userFacade;

    public RoomRes getMyRoom(User owner) {
        return roomOwnerFacade.getByOwner(owner)
                .getRoom()
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

    public List<RoomRes> getSharedRoomList(User guest) {
        return roomGuestFacade.getAllByUser(guest).stream()
                .map(RoomGuest::getRoom)
                .map(Room::toResponse)
                .toList();
    }

    public void askRoomShare(User user, AskRoomShareReq req) {
        User owner = userFacade.getUserByStudentId(req.getOwnerStudentId());
        Room room = roomOwnerFacade.getByOwner(owner)
                .getRoom();

        RoomShare roomShare = RoomShare.builder()
                .guest(user)
                .owner(owner)
                .room(room)
                .stat(RoomShareStat.WAITING)
                .build();

        roomShareFacade.save(roomShare);

        roomNotificationFacade.sendRoomShare(roomShare);
    }

    @Transactional
    public void acceptRoomShare(User user, AcceptRoomShareReq req) {
        RoomShare roomShare = roomShareFacade.getById(req.getShareId());
        roomShare.setStat(req.getStat());

        RoomGuest roomGuest = RoomGuest.builder()
                .pk(RoomGuestPk.builder()
                        .roomId(roomShare.getRoom().getId())
                        .userCode(roomShare.getGuest().getCode())
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

    @Transactional
    public void updateDoorState(User visitor, Long id, UpdateDoorStateReq req) {
        Room room = roomFacade.getRoomById(id);
        RoomAccessStat accessStat = roomFacade.accessCheck(visitor, room) ;
        if (accessStat.equals(RoomAccessStat.OTHER)) throw new ForbiddenAccessRoomException();

        room.setOpen(req.getState());

        roomNotificationFacade.sendUpdateDoorState(room, visitor);
        if (req.getState()) roomAccessFacade.saveLog(room, visitor, accessStat);
    }

    public List<RoomAccessLogRes> getAccessLog(User user) {
        Room room = roomOwnerFacade.getByOwner(user)
                .getRoom();

        return roomAccessFacade.getAllByRoom(room).stream()
                .map(RoomAccessLog::toResponse)
                .toList();
    }

}
