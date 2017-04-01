package com.belhard.misha.dao.exceptions;

public class UnknownDaoType extends RuntimeException {

    public UnknownDaoType(String message) {
        super(message);
    }

    public UnknownDaoType(String message, Throwable cause) {
        super(message, cause);
    }
}
