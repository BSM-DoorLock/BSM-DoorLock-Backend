package bssm.doorlock.domain.room.domain;

import bssm.doorlock.domain.user.domain.Student;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.presentation.dto.res.StudentRes;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomOwner {

    @EmbeddedId
    @JoinColumns({
            @JoinColumn(name = "roomId", insertable = false, updatable = false),
            @JoinColumn(name = "studentId", insertable = false, updatable = false)
    })
    private RoomOwnerPk pk;

    @ManyToOne
    @JoinColumn(name = "roomId", insertable = false, updatable = false)
    private Room room;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "studentId", insertable = false, updatable = false)
    private Student student;

    public StudentRes toResponse() {
        return StudentRes.builder()
                .name(student.getName())
                .studentId(student.getStudentId())
                .isUser(user != null)
                .build();
    }

    @Builder
    public RoomOwner(RoomOwnerPk pk, Room room, User user, Student student) {
        this.pk = pk;
        this.room = room;
        this.user = user;
        this.student = student;
    }
}
