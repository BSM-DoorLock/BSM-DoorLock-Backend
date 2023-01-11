package bssm.doorlock.domain.room.domain;

import bssm.doorlock.domain.room.presentation.dto.res.RoomRes;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.presentation.dto.res.UserRes;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

}
