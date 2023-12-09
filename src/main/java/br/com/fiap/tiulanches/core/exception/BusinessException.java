package br.com.fiap.tiulanches.core.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class BusinessException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    @Getter
	private final HttpStatus httpStatus;
    
    @Getter
    private final Object body;
       
    public BusinessException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.body = null;
    }
            
    public BusinessException(String message, HttpStatus httpStatus, Object body) {
        super(message);
        this.httpStatus = httpStatus;
        this.body = body;
    }
}
