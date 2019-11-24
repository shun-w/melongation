package org.assignment.melongation.exception;

/**
 * 异常
 */
public class MelongationException extends RuntimeException {
    private String message;

    public MelongationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
