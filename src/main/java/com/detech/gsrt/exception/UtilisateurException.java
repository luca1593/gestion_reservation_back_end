package com.detech.gsrt.exception;

public class UtilisateurException extends Exception{

    public enum CreationError {
        EMAIL_ALREDY_USED("Email deja utiliser sur un autre compte.", 500);

        private final String message;
        private final int code;

        CreationError(String message, int code) {
            this.message = message;
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public int getCode() {
            return code;
        }
    }

    private CreationError error;
    private String message;

    public UtilisateurException(CreationError error, String message) {
        this.error = error;
        this.message = message;
    }

    public CreationError getError() {
        return error;
    }

    public void setError(CreationError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
