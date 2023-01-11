package bssm.doorlock.domain.room.exception;

import bssm.doorlock.global.exception.GeneralException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomNotFoundException extends GeneralException {

    private final int statusCode = 404;
    private final String message = "방을 찾을 수 없습니다.";
}