package bssm.doorlock.domain.user.domain;

import bssm.doorlock.domain.room.domain.Room;
import bssm.doorlock.domain.user.presentation.dto.res.UserInfoRes;
import bssm.doorlock.domain.user.presentation.dto.res.UserRes;
import bssm.doorlock.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @Column(columnDefinition = "INT UNSIGNED")
    private Long code;

    @Column(length = 8)
    private String name;

    @Column(nullable = false, length = 12)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false, length = 32)
    private String oauthToken;

    @Column(length = 10)
    private String studentId;

    @OneToOne
    @JoinColumn(name = "studentId", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    private Room room;

    @Builder
    public User(Long code, String name, UserRole role, String oauthToken, String studentId, Student student, Room room) {
        this.code = code;
        this.name = name;
        this.role = role;
        this.oauthToken = oauthToken;
        this.studentId = studentId;
        this.student = student;
        this.room = room;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserInfoRes toUserInfoResponse() {
        return UserInfoRes.builder()
                .code(code)
                .role(role)
                .name(name)
                .build();
    }

    public UserRes toUserResponse() {
        return UserRes.builder()
                .code(code)
                .name(name)
                .studentId(studentId)
                .build();
    }

    public UserRedis toUserRedis() {
        return UserRedis.builder()
                .code(code)
                .name(name)
                .role(role)
                .oauthToken(oauthToken)
                .studentId(studentId)
                .build();
    }

}
