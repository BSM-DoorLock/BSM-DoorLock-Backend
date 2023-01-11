package bssm.doorlock.domain.room.presentation.dto.res;

import bssm.doorlock.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomShareRes {

    private Long id;
    private RoomRes room;
    private User owner;
    private User guest;
}
