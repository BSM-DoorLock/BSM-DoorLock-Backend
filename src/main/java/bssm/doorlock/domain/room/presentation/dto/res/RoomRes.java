package bssm.doorlock.domain.room.presentation.dto.res;

import bssm.doorlock.domain.user.presentation.dto.res.UserRes;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoomRes {

    private Long id;
    private Boolean isOpen;
    private List<UserRes> owners;
    private List<UserRes> guests;
}
