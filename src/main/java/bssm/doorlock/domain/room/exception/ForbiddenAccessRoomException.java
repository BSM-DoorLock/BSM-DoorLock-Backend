package bssm.doorlock.domain.room.exception;

import bssm.doorlock.global.exception.GeneralException;
import lombok.Getter;

@Getter
public class ForbiddenAccessRoomException extends GeneralException {

    private final int statusCode = 403;
    private final String message = "방에 액세스할 수 있는 권한이 없습니다.";
}