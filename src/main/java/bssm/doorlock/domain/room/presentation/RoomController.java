package bssm.doorlock.domain.room.presentation;

import bssm.doorlock.domain.room.presentation.dto.res.RoomRes;
import bssm.doorlock.domain.room.service.RoomService;
import bssm.doorlock.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("room")
@RequiredArgsConstructor
public class RoomController {

    private final UserUtil userUtil;
    private final RoomService roomService;

    @GetMapping("my")
    public RoomRes getMyRoom() {
        return roomService.getMyRoom(userUtil.getUser());
    }

    @GetMapping("{roomId}")
    public RoomRes getRoomById(@PathVariable Long roomId) {
        return roomService.getRoomById(roomId);
    }

    @GetMapping
    public List<RoomRes> getAllRoomList() {
        return roomService.getAllRoomList();
    }

}
