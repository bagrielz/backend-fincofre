package br.com.fincofre.api.infra.exception;

import br.com.fincofre.api.exceptions.SpentNotFoundException;
import br.com.fincofre.api.exceptions.ValidationException;
import br.com.fincofre.api.models.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDTO> handleValidation(ValidationException ex) {
        var err = new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(SpentNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleSpentNotFound(SpentNotFoundException ex) {
        var err = new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}
