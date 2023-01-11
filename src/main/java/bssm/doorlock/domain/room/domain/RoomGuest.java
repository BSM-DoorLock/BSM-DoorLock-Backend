package bssm.doorlock.domain.room.domain;

import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.presentation.dto.res.UserRes;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomGuest {

    @EmbeddedId
    @JoinColumns({
            @JoinColumn(name = "roomId", insertable = false, updatable = false),
            @JoinColumn(name = "userCode", insertable = false, updatable = false)
    })
    private RoomGuestPk pk;

    @ManyToOne
    @JoinColumn(name = "roomId", insertable = false, updatable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "userCode", insertable = false, updatable = false)
    private User user;

    public UserRes toResponse() {
        return user.toUserResponse();
    }

    @Builder
    public RoomGuest(RoomGuestPk pk, Room room, User user) {
        this.pk = pk;
        this.room = room;
        this.user = user;
    }
}
