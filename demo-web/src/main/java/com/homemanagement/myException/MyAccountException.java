package com.homemanagement.myException;

public class MyAccountException extends RuntimeException {
    public MyAccountException() {
    }

    public MyAccountException(String message) {
        super(message);
    }

    public MyAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyAccountException(Throwable cause) {
        super(cause);
    }

    public MyAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
