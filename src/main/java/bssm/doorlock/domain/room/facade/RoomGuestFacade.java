package bssm.doorlock.domain.room.facade;

import bssm.doorlock.domain.room.domain.RoomGuest;
import bssm.doorlock.domain.room.domain.RoomShare;
import bssm.doorlock.domain.room.domain.repository.RoomGuestRepository;
import bssm.doorlock.domain.room.domain.repository.RoomShareRepository;
import bssm.doorlock.domain.room.exception.RoomNotFoundException;
import bssm.doorlock.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomGuestFacade {

    private final RoomGuestRepository roomGuestRepository;

    public List<RoomGuest> getAllByUser(User guest) {
        return roomGuestRepository.findAllByUser(guest);
    }

    public RoomGuest save(RoomGuest roomGuest) {
        return roomGuestRepository.save(roomGuest);
    }

}
