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

    @Builder
    public RoomAccessLog(Long id, Room room, User user) {
        this.id = id;
        this.room = room;
        this.user = user;
    }

    public RoomAccessLogRes toResponse() {
        return RoomAccessLogRes.builder()
                .id(id)
                .room(room)
                .user(user)
                .accessedAt(getCreatedAt())
                .build();
    }
}
