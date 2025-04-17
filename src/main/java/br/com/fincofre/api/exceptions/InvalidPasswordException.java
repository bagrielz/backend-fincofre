package br.com.fincofre.api.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String s) {
        super(s);
    }
}
