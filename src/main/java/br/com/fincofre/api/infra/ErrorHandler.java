package br.com.fincofre.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Classe para tratar erros
@RestControllerAdvice
public class ErrorHandler {

    // Método para tratar o erro 404
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandling404() {
        return ResponseEntity.notFound().build();
    }

    // Método para tratar o erro 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandling400(MethodArgumentNotValidException ex) {
        var err = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(err.stream().map(ErrorValidationDTO::new).toList());
    }

    // DTO para retornar os campos da resposta
    private record ErrorValidationDTO(String field, String message) {
        public ErrorValidationDTO(FieldError err) {
            this(err.getField(), err.getDefaultMessage());
        }
    }

}
