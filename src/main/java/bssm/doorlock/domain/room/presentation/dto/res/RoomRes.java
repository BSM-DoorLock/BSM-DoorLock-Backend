package bssm.doorlock.domain.room.presentation.dto.res;

import bssm.doorlock.domain.user.presentation.dto.res.UserRes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RoomRes {

    private Long id;
    private Boolean isOpen;
    private List<UserRes> owners;
    private List<UserRes> guests;
}
