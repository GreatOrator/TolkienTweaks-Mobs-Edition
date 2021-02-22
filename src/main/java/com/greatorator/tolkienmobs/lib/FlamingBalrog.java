package com.greatorator.tolkienmobs.lib;

/** Borrowed from Draconic Evolution */
public class FlamingBalrog extends RuntimeException {
    public FlamingBalrog() {}

    public FlamingBalrog(String message) {
        super(message);
    }

    public FlamingBalrog(String message, Throwable cause) {
        super(message, cause);
    }

    public FlamingBalrog(Throwable cause) {
        super(cause);
    }

    public FlamingBalrog(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
