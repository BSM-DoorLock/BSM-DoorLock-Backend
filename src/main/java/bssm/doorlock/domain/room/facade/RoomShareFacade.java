package bssm.doorlock.domain.room.facade;

import bssm.doorlock.domain.room.domain.RoomShare;
import bssm.doorlock.domain.room.domain.repository.RoomShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomShareFacade {

    private final RoomShareRepository roomShareRepository;

    public RoomShare save(RoomShare roomShare) {
        return roomShareRepository.save(roomShare);
    }

}
