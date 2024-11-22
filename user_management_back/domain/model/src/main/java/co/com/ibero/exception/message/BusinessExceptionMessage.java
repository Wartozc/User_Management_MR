package co.com.ibero.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BusinessExceptionMessage {
    UN_EXPECTED_ERROR("UB0001", "An un-expected error was presented");

    private final String code;
    private final String message;


}
