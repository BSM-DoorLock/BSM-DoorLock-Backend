package bssm.doorlock.domain.room.presentation;

import bssm.doorlock.domain.room.presentation.dto.res.RoomRankingRes;
import bssm.doorlock.domain.room.service.RoomRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("room")
@RequiredArgsConstructor
public class RoomRankingController {

    private final RoomRankingService roomRankingService;

    @GetMapping("ranking/room")
    public List<RoomRankingRes> orderBySharedRoom() {
        return roomRankingService.orderBySharedRoom();
    }
}
