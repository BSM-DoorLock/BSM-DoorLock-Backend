package bssm.doorlock.domain.user.domain;

import bssm.doorlock.domain.user.presentation.dto.res.UserRes;
import bssm.doorlock.domain.user.presentation.dto.res.StudentRes;
import bssm.doorlock.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @Column(columnDefinition = "INT UNSIGNED")
    private Long code;

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

    @Builder
    public User(Long code, UserRole role, String oauthToken, String studentId, Student student) {
        this.code = code;
        this.role = role;
        this.oauthToken = oauthToken;
        this.studentId = studentId;
        this.student = student;
    }

    public UserRes toUserResponse() {
        return UserRes.builder()
                .code(code)
                .role(role)
                .name(student.getName())
                .build();
    }

    public StudentRes toStudentResponse() {
        return StudentRes.builder()
                .name(student.getName())
                .studentId(studentId)
                .isUser(true)
                .build();
    }

    public UserRedis toUserRedis() {
        return UserRedis.builder()
                .code(code)
                .role(role)
                .oauthToken(oauthToken)
                .studentId(studentId)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(code, user.code);
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
