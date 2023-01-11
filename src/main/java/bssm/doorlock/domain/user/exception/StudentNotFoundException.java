package bssm.doorlock.domain.user.exception;

import bssm.doorlock.global.exception.GeneralException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentNotFoundException extends GeneralException {

    private final int statusCode = 404;
    private String message = "Student Not Found";

    public StudentNotFoundException(String message) {
        this.message = message;
    }
}