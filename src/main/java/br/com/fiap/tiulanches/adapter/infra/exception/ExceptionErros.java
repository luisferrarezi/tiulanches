package br.com.fiap.tiulanches.adapter.infra.exception;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionErros {
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity notFoundError() {
		return ResponseEntity.notFound().build();	
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity notValidError(MethodArgumentNotValidException ex) {
		List<FieldError> erros = ex.getFieldErrors();
		
		return ResponseEntity.badRequest().body(erros.stream().map(ErroValidacao::new).toList());	
	}	
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity notValidConstraintError(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> erros = ex.getConstraintViolations();
		
		return ResponseEntity.badRequest().body(erros.stream().map(ErroValidacao::new).toList());	
	}		
}
