package bssm.doorlock.domain.room.presentation.dto.res;

import bssm.doorlock.domain.user.presentation.dto.res.StudentRes;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomShareNotificationRes {

    private StudentRes owner;
    private StudentRes guest;
}
