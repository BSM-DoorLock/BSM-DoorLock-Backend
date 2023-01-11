package bssm.doorlock.domain.room.presentation.dto.res;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.presentation.dto.res.StudentRes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomAccessLogRes {

    private Long id;
    private Long roomId;
    private StudentRes user;
    private LocalDateTime accessedAt;
}
