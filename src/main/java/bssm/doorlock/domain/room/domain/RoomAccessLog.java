package bssm.doorlock.domain.room.domain;

import bssm.doorlock.domain.room.presentation.dto.res.RoomAccessLogRes;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomAccessLog extends BaseTimeEntity {

    @Id
    @Column(columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Room room;

    @OneToOne
    private User user;

    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    RoomAccessStat accessStat;

    @Builder
    public RoomAccessLog(Long id, Room room, User user, RoomAccessStat accessStat) {
        this.id = id;
        this.room = room;
        this.user = user;
        this.accessStat = accessStat;
    }

    public RoomAccessLogRes toResponse() {
        return RoomAccessLogRes.builder()
                .id(id)
                .roomId(room.getId())
                .user(user.toStudentResponse())
                .accessStat(accessStat)
                .accessedAt(getCreatedAt())
                .build();
    }
}
