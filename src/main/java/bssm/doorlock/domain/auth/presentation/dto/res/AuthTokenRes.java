package bssm.doorlock.domain.auth.presentation.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthTokenRes {

    private String accessToken;
    private String refreshToken;
}
