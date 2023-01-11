package bssm.doorlock.domain.auth.facade;

import bssm.doorlock.domain.auth.domain.RefreshToken;
import bssm.doorlock.domain.auth.domain.repository.RefreshTokenRepository;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.global.exception.RefreshTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken getRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByTokenAndIsAvailable(refreshToken, true)
                .orElseThrow(RefreshTokenNotFoundException::new);
    }

    public User getUserByRefreshToken(String refreshToken) {
        return getRefreshToken(refreshToken)
                .getUser();
    }
}
