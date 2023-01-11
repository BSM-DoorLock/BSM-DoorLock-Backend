package bssm.doorlock.domain.room.domain;

import bssm.doorlock.domain.room.presentation.dto.res.RoomShareRes;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.domain.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomShare {

    @Id
    @Column(columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Room room;

    @OneToOne
    private User owner;

    @OneToOne
    private User guest;

    @Column(nullable = false, length = 7)
    @Enumerated(EnumType.STRING)
    private RoomShareStat stat;

    @Builder
    public RoomShare(Long id, Room room, User owner, User guest, RoomShareStat stat) {
        this.id = id;
        this.room = room;
        this.owner = owner;
        this.guest = guest;
        this.stat = stat;
    }

    public RoomShareRes toResponse() {
        return RoomShareRes.builder()
                .id(id)
                .room(room.toShareResponse())
                .owner(owner.toStudentResponse())
                .guest(guest.toStudentResponse())
                .stat(stat)
                .build();
    }

    public void setStat(RoomShareStat stat) {
        this.stat = stat;
    }

}
