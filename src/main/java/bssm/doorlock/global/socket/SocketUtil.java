package bssm.doorlock.global.socket;

import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.global.async.service.AsyncService;
import bssm.doorlock.global.socket.domain.SocketEvent;
import bssm.doorlock.global.socket.service.SocketClientProvider;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SocketUtil {

    private final SocketClientProvider socketClientProvider;
    private final AsyncService asyncService;

    public void sendMessageToUser(User user, String socketEvent, Object msg) {
        List<SocketIOClient> clientList = socketClientProvider.findAllByUser(user);
        clientList.forEach(client ->
                asyncService.run(() -> client.sendEvent(socketEvent, msg))
        );
    }

    public void sendMessageToDoorClient(Long roomId, String socketEvent, Object msg) {
        List<SocketIOClient> clientList = socketClientProvider.findAllByRoomClient(roomId);
        clientList.forEach(client ->
                asyncService.run(() -> client.sendEvent(socketEvent, msg))
        );
    }
}
