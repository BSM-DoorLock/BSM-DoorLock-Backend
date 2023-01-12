package bssm.doorlock.domain.room.facade;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.room.domain.RoomShare;
import bssm.doorlock.domain.room.presentation.dto.res.RoomAccessNotificationRes;
import bssm.doorlock.domain.room.presentation.dto.res.RoomShareNotificationRes;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.global.socket.SocketUtil;
import bssm.doorlock.global.socket.domain.SocketEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomNotificationFacade {

    private final SocketUtil socketUtil;

    public void sendRoomShare(RoomShare roomShare) {
        RoomShareNotificationRes res = RoomShareNotificationRes.builder()
                .shareId(roomShare.getId())
                .owner(roomShare.getOwner().toStudentResponse())
                .guest(roomShare.getGuest().toStudentResponse())
                .build();

        socketUtil.sendMessageToUser(roomShare.getOwner(), SocketEvent.RECEIVE_ROOM_SHARE, res);
    }

    public void sendUpdateDoorState(Room room, User visitor) {
        sendUpdateDoorStateToUserClient(visitor, room);
        sendUpdateDoorStateToRoomClient(room);
    }

    private void sendUpdateDoorStateToUserClient(User visitor, Room room) {
        RoomAccessNotificationRes res = RoomAccessNotificationRes.builder()
                .roomId(room.getId())
                .user(visitor.toStudentResponse())
                .isOpen(room.getIsOpen())
                .build();

        room.getOwners().forEach(owner -> {
            if (owner.getUser() == null || owner.getUser().equals(visitor)) return;
            socketUtil.sendMessageToUser(owner.getUser(), SocketEvent.UPDATE_DOOR_STATE, res);
        });
    }

    private void sendUpdateDoorStateToRoomClient(Room room) {
        socketUtil.sendMessageToDoorClient(room.getId(), SocketEvent.UPDATE_DOOR_STATE, room.getIsOpen());
    }
}
