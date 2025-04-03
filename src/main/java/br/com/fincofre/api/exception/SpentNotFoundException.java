package br.com.fincofre.api.exception;

public class SpentNotFoundException extends RuntimeException {
    public SpentNotFoundException(String s) {
        super(s);
    }
}
