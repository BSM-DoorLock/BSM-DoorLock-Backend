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
    private String studentId;
    private String oauthToken;

    @Builder
    public UserRedis(Long code, String nickname, UserRole role, String studentId, String oauthToken) {
        this.code = code;
        this.nickname = nickname;
        this.role = role;
        this.studentId = studentId;
        this.oauthToken = oauthToken;
    }

    public User toUser() {
        return User.builder()
                .code(code)
                .nickname(nickname)
                .role(role)
                .studentId(studentId)
                .oauthToken(oauthToken)
                .build();
    }

}
