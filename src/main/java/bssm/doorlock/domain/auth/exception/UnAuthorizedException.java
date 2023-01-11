package bssm.doorlock.domain.auth.exception;

import bssm.doorlock.global.exception.GeneralException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UnAuthorizedException extends GeneralException {

    private final int statusCode = 401;
    private String message = "UnAuthorized";

    public UnAuthorizedException(String message) {
        this.message = message;
    }
}