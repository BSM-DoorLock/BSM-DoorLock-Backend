package bssm.doorlock.domain.auth.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthLoginRes {

    private String accessToken;
    private String refreshToken;
}
