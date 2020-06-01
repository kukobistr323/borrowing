package com.borrowing.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Vlad Kukoba
 */
@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class NotValidPrincipal extends RuntimeException {

    public NotValidPrincipal() {
    }

    public NotValidPrincipal(String message) {
        super(message);
    }

    public NotValidPrincipal(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidPrincipal(Throwable cause) {
        super(cause);
    }

    public NotValidPrincipal(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
