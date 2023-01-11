package bssm.doorlock.domain.room.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@RequiredArgsConstructor
@Embeddable
public class RoomOwnerPk implements Serializable {

    @Column(columnDefinition = "INT UNSIGNED")
    private Long roomId;

    @Column
    private String studentId;

    @Builder
    public RoomOwnerPk(Long roomId, String studentId) {
        this.roomId = roomId;
        this.studentId = studentId;
    }
}
