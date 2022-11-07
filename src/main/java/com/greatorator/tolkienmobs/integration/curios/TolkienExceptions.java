package com.greatorator.tolkienmobs.integration.curios;

/** Borrowed from Draconic Evolution */
public class TolkienExceptions extends RuntimeException {
    public TolkienExceptions() {}

    public TolkienExceptions(String message) {
        super(message);
    }

    public TolkienExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public TolkienExceptions(Throwable cause) {
        super(cause);
    }

    public TolkienExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
