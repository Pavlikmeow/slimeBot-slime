package com.titanov.slimeBotslime.exception;

public class FightNotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public FightNotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
