package com.usdtl.ims.common.exceptions;

public class RequestException extends RuntimeException {
    public RequestException(Throwable cause) {
        super(cause);
    }

    public RequestException(String message) {
        super(message);
    }
}

