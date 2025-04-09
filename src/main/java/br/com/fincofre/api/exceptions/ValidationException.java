package br.com.fincofre.api.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String s) {
        super(s);
    }

}
