package br.com.fiap.tiulanches.core.exception;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.transaction.CannotCreateTransactionException;

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
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity notValidValueError(MethodArgumentTypeMismatchException ex) {
		FieldError erros = new FieldError(ex.getClass().toString(), ex.getName(), "Valor informado inválido!");
		
		return ResponseEntity.badRequest().body(new ErroValidacao(erros));	
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity businessError(BusinessException ex) {
		FieldError erros = new FieldError(ex.getClass().toString(), ex.getBody().toString(), ex.getMessage());
		
		ResponseEntity response;

		if (ex.getHttpStatus() == HttpStatus.UNAUTHORIZED) {
			response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErroValidacao(erros));
		} else {
			response = ResponseEntity.badRequest().body(new ErroValidacao(erros));	
		}

		return response;
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> dataIntegrityViolationError(DataIntegrityViolationException ex) {
		ErroValidacao erro = new ErroValidacao("Exclusão", "Este registro está vinculado a outro, não pode ser excluído!");
		
		return new ResponseEntity <Object>(erro, HttpStatus.BAD_REQUEST);	
	}		
	
	@ExceptionHandler(CannotCreateTransactionException.class)
	public ResponseEntity<Object> notDataBaseConnectionError(CannotCreateTransactionException ex) {
		ErroValidacao erro = new ErroValidacao("Conexão", "Falha de conexão, tente novamente mais tarde!");
		
		return new ResponseEntity <Object>(erro, HttpStatus.BAD_GATEWAY);	
	}		
}
