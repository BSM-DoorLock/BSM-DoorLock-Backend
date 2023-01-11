package bssm.doorlock.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class BadRequestException extends RuntimeException {

    private final Map<String, String> fields;
}