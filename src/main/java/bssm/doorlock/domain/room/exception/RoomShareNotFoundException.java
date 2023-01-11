package bssm.doorlock.domain.room.exception;

import bssm.doorlock.global.exception.GeneralException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomShareNotFoundException extends GeneralException {

    private final int statusCode = 404;
    private String message = "방 공유 요청을 찾을 수 없습니다.";

    public RoomShareNotFoundException(String message) {
        this.message = message;
    }
}