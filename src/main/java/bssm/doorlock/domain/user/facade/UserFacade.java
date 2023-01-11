package bssm.doorlock.domain.user.facade;

import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.domain.repository.RedisUserRepository;
import bssm.doorlock.domain.auth.domain.repository.RefreshTokenRepository;
import bssm.doorlock.domain.user.exception.UserNotFoundException;
import bssm.doorlock.global.exception.RefreshTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final RedisUserRepository userRedisRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public User getByAvailableRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByTokenAndIsAvailable(refreshToken, true)
                .orElseThrow(RefreshTokenNotFoundException::new)
                .getUser();
    }

    public User getCachedUserByCode(long userCode) {
        return userRedisRepository.findById(userCode)
                .orElseThrow(UserNotFoundException::new)
                .toUser();
    }

    public void saveCacheUser(User user) {
        userRedisRepository.save(user.toUserRedis());
    }

}
