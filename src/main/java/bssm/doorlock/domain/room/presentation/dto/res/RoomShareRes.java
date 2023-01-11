package bssm.doorlock.domain.room.presentation.dto.res;

import bssm.doorlock.domain.room.domain.RoomShareStat;
import bssm.doorlock.domain.user.presentation.dto.res.StudentRes;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomShareRes {

    private Long id;
    private RoomRes room;
    private StudentRes owner;
    private StudentRes guest;
    private RoomShareStat stat;
}
