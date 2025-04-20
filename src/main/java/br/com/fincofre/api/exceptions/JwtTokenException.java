package br.com.fincofre.api.exceptions;

public class JwtTokenException extends RuntimeException {
    public JwtTokenException(String s) {
        super(s);
    }
}
