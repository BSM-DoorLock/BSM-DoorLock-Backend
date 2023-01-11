package bssm.doorlock.domain.room.presentation;

import bssm.doorlock.domain.room.presentation.dto.res.RoomRes;
import bssm.doorlock.domain.room.service.RoomService;
import bssm.doorlock.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room")
@RequiredArgsConstructor
public class RoomController {

    private final UserUtil userUtil;
    private final RoomService roomService;

    @GetMapping
    public RoomRes getMyRoom() {
        return roomService.getMyRoom(userUtil.getUser());
    }
}
