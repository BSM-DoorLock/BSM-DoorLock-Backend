package bssm.doorlock.domain.room.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@RequiredArgsConstructor
@Embeddable
public class RoomGuestPk implements Serializable {

    @Column(columnDefinition = "INT UNSIGNED")
    private Long roomId;

    @Column(columnDefinition = "INT UNSIGNED")
    private Long userCode;

    @Builder
    public RoomGuestPk(Long roomId, Long userCode) {
        this.roomId = roomId;
        this.userCode = userCode;
    }
}
