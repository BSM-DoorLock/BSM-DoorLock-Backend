package bssm.doorlock.domain.room.presentation.dto.res;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomAccessLogRes {

    private Long id;
    private Room room;
    private User user;
    private LocalDateTime accessedAt;
}
