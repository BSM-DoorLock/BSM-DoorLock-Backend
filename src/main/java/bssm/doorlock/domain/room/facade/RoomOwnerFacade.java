package bssm.doorlock.domain.room.facade;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.room.domain.RoomOwner;
import bssm.doorlock.domain.room.domain.repository.RoomOwnerRepository;
import bssm.doorlock.domain.room.exception.RoomNotFoundException;
import bssm.doorlock.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomOwnerFacade {

    private final RoomOwnerRepository roomOwnerRepository;

    public RoomOwner getByOwner(User owner) {
        return roomOwnerRepository.findByUser(owner)
                .orElseThrow(RoomNotFoundException::new);
    }

}
