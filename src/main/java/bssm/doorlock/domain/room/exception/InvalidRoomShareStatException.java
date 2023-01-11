package bssm.doorlock.domain.room.exception;

import bssm.doorlock.global.exception.BadRequestException;
import com.google.common.collect.ImmutableMap;
import lombok.Getter;

@Getter
public class InvalidRoomShareStatException extends BadRequestException {

    public InvalidRoomShareStatException() {
        super(ImmutableMap.<String, String>builder().
                put("stat", "잘못된 공유 상태입니다.").
                build());
    }
}