package bssm.doorlock.domain.room.facade;

import bssm.doorlock.domain.room.domain.RoomShare;
import bssm.doorlock.domain.room.domain.repository.RoomShareRepository;
import bssm.doorlock.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomShareFacade {

    private final RoomShareRepository roomShareRepository;

    public List<RoomShare> getAskShareList(User guest) {
        return roomShareRepository.findAllByGuest(guest);
    }

    public List<RoomShare> getReceiveShareList(User owner) {
        return roomShareRepository.findAllByOwner(owner);
    }

    public RoomShare save(RoomShare roomShare) {
        return roomShareRepository.save(roomShare);
    }

}
