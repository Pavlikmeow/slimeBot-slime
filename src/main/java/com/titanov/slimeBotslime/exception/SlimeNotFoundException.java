package com.titanov.slimeBotslime.exception;

public class SlimeNotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public SlimeNotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
