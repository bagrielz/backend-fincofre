package br.com.fincofre.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
// Classe para tratar erros
public class ErrorHandler {

    // Método para tratar o erro 404
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandling404() {
        return ResponseEntity.notFound().build();
    }

}
