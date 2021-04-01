package br.com.estudo.cervejaria.util.exception;

public abstract class BusinessErrorException extends RuntimeException {
    public BusinessErrorException() {
        super();
    }

    public BusinessErrorException(String message) {
        super(message);
    }

    public BusinessErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessErrorException(Throwable cause) {
        super(cause);
    }

    protected BusinessErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
