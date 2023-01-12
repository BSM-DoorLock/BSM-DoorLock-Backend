package bssm.doorlock.domain.room.service;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.room.domain.repository.RoomRepository;
import bssm.doorlock.domain.room.facade.RoomFacade;
import bssm.doorlock.domain.room.presentation.dto.res.RoomRankingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomRankingService {

    private final RoomFacade roomFacade;

    public List<RoomRankingRes> orderBySharedRoom() {
        return roomFacade.getAllRoomList().stream()
                .map(Room::toRankingResponse)
                .sorted()
                .toList();
    }

}
