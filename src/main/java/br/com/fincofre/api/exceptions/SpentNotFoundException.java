package br.com.fincofre.api.exceptions;

public class SpentNotFoundException extends RuntimeException {
    public SpentNotFoundException(String s) {
        super(s);
    }
}
