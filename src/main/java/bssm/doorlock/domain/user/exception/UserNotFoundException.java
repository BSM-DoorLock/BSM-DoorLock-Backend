package bssm.doorlock.domain.user.exception;

import bssm.doorlock.global.exception.GeneralException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserNotFoundException extends GeneralException {

    private final int statusCode = 404;
    private String message = "User Not Found";

    public UserNotFoundException(String message) {
        this.message = message;
    }
}