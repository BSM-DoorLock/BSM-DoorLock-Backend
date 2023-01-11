package bssm.doorlock.domain.room.presentation.dto.req;

import bssm.doorlock.domain.room.domain.RoomShareStat;
import bssm.doorlock.domain.room.exception.InvalidRoomShareStatException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AcceptRoomShareReq {

    @NotNull
    private Long shareId;

    @NotNull
    private RoomShareStat stat;

    public AcceptRoomShareReq(Long shareId, RoomShareStat stat) {
        if (stat.equals(RoomShareStat.WAITING)) {
            throw new InvalidRoomShareStatException();
        }
        this.shareId = shareId;
        this.stat = stat;
    }
}
