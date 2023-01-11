package bssm.doorlock.domain.user.domain;

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

    @Column(nullable = false, length = 40, unique = true)
    private String nickname;

    @Column(nullable = false, length = 12)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false, length = 32)
    private String oauthToken;

    @Column(length = 10)
    private String studentId;

    @Builder
    public User(Long code, String nickname, UserRole role, String oauthToken, String studentId) {
        this.code = code;
        this.nickname = nickname;
        this.role = role;
        this.oauthToken = oauthToken;
        this.studentId = studentId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UserRedis toUserRedis() {
        return UserRedis.builder()
                .code(code)
                .nickname(nickname)
                .role(role)
                .oauthToken(oauthToken)
                .studentId(studentId)
                .build();
    }

}
