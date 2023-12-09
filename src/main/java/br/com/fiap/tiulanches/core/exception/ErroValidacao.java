package br.com.fiap.tiulanches.core.exception;

import org.springframework.validation.FieldError;

import jakarta.validation.ConstraintViolation;

public record ErroValidacao(String campo, String mensagem) {

	public ErroValidacao(FieldError erro) {
		this(erro.getField(), erro.getDefaultMessage());
	}
	
	public ErroValidacao(ConstraintViolation<?> erro) {
		this(erro.getPropertyPath().toString(), erro.getMessage());
	}
}
