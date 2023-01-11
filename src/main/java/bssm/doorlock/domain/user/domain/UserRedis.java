package bssm.doorlock.domain.user.domain;

import bssm.doorlock.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "user")
public class UserRedis extends BaseTimeEntity {

    @Id
    private Long code;
    private String nickname;
    private UserRole role;
    private String oauthToken;
    private String studentId;
    private Student student;

    @Builder
    public UserRedis(Long code, String nickname, UserRole role, String oauthToken, String studentId, Student student) {
        this.code = code;
        this.nickname = nickname;
        this.role = role;
        this.oauthToken = oauthToken;
        this.studentId = studentId;
        this.student = student;
    }

    public User toUser() {
        return User.builder()
                .code(code)
                .nickname(nickname)
                .role(role)
                .oauthToken(oauthToken)
                .studentId(studentId)
                .student(student)
                .build();
    }

}
