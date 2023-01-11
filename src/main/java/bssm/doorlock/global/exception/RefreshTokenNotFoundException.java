package bssm.doorlock.global.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshTokenNotFoundException extends GeneralException {

    private final int statusCode = 404;
    private String message = "Refresh Token Not Found";

    public RefreshTokenNotFoundException(String message) {
        this.message = message;
    }
}