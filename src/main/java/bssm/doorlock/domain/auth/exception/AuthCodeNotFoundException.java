package bssm.doorlock.domain.auth.exception;

import bssm.doorlock.global.exception.GeneralException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthCodeNotFoundException extends GeneralException {

    private final int statusCode = 404;
    private String message = "BSM OAuth 인증코드를 찾을 수 없습니다.";

    public AuthCodeNotFoundException(String message) {
        this.message = message;
    }
}