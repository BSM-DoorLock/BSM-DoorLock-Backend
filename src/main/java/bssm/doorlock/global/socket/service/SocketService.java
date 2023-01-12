package bssm.doorlock.global.socket.service;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.room.exception.RoomNotFoundException;
import bssm.doorlock.domain.room.facade.RoomFacade;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.facade.UserFacade;
import bssm.doorlock.global.jwt.JwtProvider;
import bssm.doorlock.global.socket.domain.SocketEvent;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final SocketIOServer socketIOServer;
    private final SocketClientProvider socketClientProvider;
    private final JwtProvider jwtProvider;
    private final UserFacade userFacade;
    private final RoomFacade roomFacade;


    @PostConstruct
    private void autoStartup() {
        socketIOServer.addConnectListener(this::onConnect);

        socketIOServer.addDisconnectListener(socketClientProvider::removeClient);

        socketIOServer.start();

        socketIOServer.addEventListener(SocketEvent.STUDENT_AUTH, String.class, studentAuthentication);
        socketIOServer.addEventListener(SocketEvent.ROOM_AUTH, Long.class, roomAuthentication);
    }

    @PreDestroy
    private void autoStop() {
        socketIOServer.stop();
    }

    private void onConnect(SocketIOClient client) {

    }

    private final DataListener<String> studentAuthentication = new DataListener<>() {
        @Override
        public void onData(SocketIOClient client, String token, AckRequest ackRequest) {
            try {
                User user = userFacade.getByCode(jwtProvider.getUserCode(token));
                socketClientProvider.addClient(user, client);
            } catch (Exception e) {
                e.printStackTrace();
                client.sendEvent(SocketEvent.UNAUTHORIZED);
            }
        }
    };

    private final DataListener<Long> roomAuthentication = new DataListener<>() {
        @Override
        public void onData(SocketIOClient client, Long roomId, AckRequest ackRequest) {
            try {
                Room room = roomFacade.getRoomById(roomId);
                socketClientProvider.addRoomClient(room.getId(), client);
            } catch (RoomNotFoundException e) {
                client.sendEvent(SocketEvent.ERROR, "Room not found");
            }
        }
    };

}