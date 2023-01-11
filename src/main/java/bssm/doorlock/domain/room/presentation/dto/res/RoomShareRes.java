package bssm.doorlock.domain.room.presentation.dto.res;

import bssm.doorlock.domain.room.domain.RoomShareStat;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.presentation.dto.res.UserRes;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomShareRes {

    private Long id;
    private RoomRes room;
    private UserRes owner;
    private UserRes guest;
    private RoomShareStat stat;
}
