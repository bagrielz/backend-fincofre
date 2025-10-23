package br.com.fincofre.api.infra.exception;

import br.com.fincofre.api.exceptions.JwtTokenException;
import br.com.fincofre.api.exceptions.SpentNotFoundException;
import br.com.fincofre.api.exceptions.ValidationException;
import br.com.fincofre.api.models.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorValidationDTO>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var err = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(err.stream().map(ErrorValidationDTO::new).toList());
    }

    @ExceptionHandler(JwtTokenException.class)
    public ResponseEntity<ErrorDTO> handleJwtToken(JwtTokenException ex) {
        var err = new ErrorDTO(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDTO> handleValidation(ValidationException ex) {
        var err = new ErrorDTO(HttpStatus.FORBIDDEN.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }

    @ExceptionHandler(SpentNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleSpentNotFound(SpentNotFoundException ex) {
        var err = new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUsernameNotFound(UsernameNotFoundException ex) {
        var err = new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    public record ErrorValidationDTO(String field, String message) {
        public ErrorValidationDTO(FieldError err) {
            this(err.getField(), err.getDefaultMessage());
        }
    }

}
