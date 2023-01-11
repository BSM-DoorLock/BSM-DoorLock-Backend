package bssm.doorlock.domain.auth.exception;

import bssm.doorlock.global.exception.GeneralException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InvalidOauthClientException extends GeneralException {

    private final int statusCode = 500;
    private String message = "BSM OAuth 클라이언트 인증에 실패하였습니다.";

    public InvalidOauthClientException(String message) {
        this.message = message;
    }
}