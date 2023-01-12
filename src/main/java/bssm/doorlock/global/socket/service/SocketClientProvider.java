package bssm.doorlock.global.socket.service;

import bssm.doorlock.domain.user.domain.User;
import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SocketClientProvider {

    private final Map<Long, List<SocketIOClient>> userClientMap = new ConcurrentHashMap<>();
    private final Map<SocketIOClient, User> clientMap = new ConcurrentHashMap<>();

    private final Map<Long, List<SocketIOClient>> roomClientMap = new ConcurrentHashMap<>();

    public void addClient(User user, SocketIOClient client) {
        List<SocketIOClient> clientList = Optional.ofNullable(userClientMap.get(user.getCode()))
                .orElseGet(ArrayList::new);
        clientList.add(client);
        userClientMap.put(user.getCode(), clientList);
        clientMap.put(client, user);
    }

    public void addRoomClient(Long roomId, SocketIOClient client) {
        List<SocketIOClient> clientList = Optional.ofNullable(userClientMap.get(roomId))
                .orElseGet(ArrayList::new);
        clientList.add(client);
        roomClientMap.put(roomId, clientList);
    }

    public void removeClient(SocketIOClient client) {
        Optional<User> userOptional = Optional.ofNullable(clientMap.get(client));
        if (userOptional.isEmpty()) return;
        User user = userOptional.get();

        List<SocketIOClient> clientList = Optional.ofNullable(userClientMap.get(user.getCode()))
                .orElseGet(ArrayList::new);
        clientList.remove(client);
        userClientMap.put(user.getCode(), clientList);
        clientMap.remove(client);
    }

    public List<SocketIOClient> findAllByUser(User user) {
        return Optional.ofNullable(userClientMap.get(user.getCode()))
                .orElseGet(ArrayList::new);
    }

    public List<SocketIOClient> findAllByRoomClient(Long roomId) {
        return Optional.ofNullable(roomClientMap.get(roomId))
                .orElseGet(ArrayList::new);
    }

}
