package de.jhamel.wdtranslator;

public class TechnicalException extends RuntimeException {

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
