package bssm.doorlock.domain.room.presentation;

import bssm.doorlock.domain.room.presentation.dto.req.AcceptRoomShareReq;
import bssm.doorlock.domain.room.presentation.dto.req.AskRoomShareReq;
import bssm.doorlock.domain.room.presentation.dto.res.RoomRes;
import bssm.doorlock.domain.room.presentation.dto.res.RoomShareRes;
import bssm.doorlock.domain.room.service.RoomService;
import bssm.doorlock.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("share/ask")
    public void askRoomShare(@Valid @RequestBody AskRoomShareReq req) {
        roomService.askRoomShare(userUtil.getUser(), req);
    }

    @PostMapping("share/accept")
    public void acceptRoomShare(@Valid @RequestBody AcceptRoomShareReq req) {
        roomService.acceptRoomShare(userUtil.getUser(), req);
    }

    @GetMapping("share/ask")
    public List<RoomShareRes> getAskShareList() {
        return roomService.getAskShareList(userUtil.getUser());
    }

    @GetMapping("share/receive")
    public List<RoomShareRes> getReceiveShareList() {
        return roomService.getReceiveShareList(userUtil.getUser());
    }

}
