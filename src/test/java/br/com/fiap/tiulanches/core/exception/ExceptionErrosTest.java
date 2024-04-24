package br.com.fiap.tiulanches.core.exception;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@SuppressWarnings("rawtypes")
class ExceptionErrosTest {

    private ExceptionErros exceptionErros;    
    private ResponseEntity response;

    @BeforeEach
    void beforeEach(){
        exceptionErros = new ExceptionErros();
    }

    @Test    
    void notFoundErrorTest(){        
        response = exceptionErros.notFoundError();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void notValidErrorTest(){
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        
        response = exceptionErros.notValidError(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void notValidConstraintErrorTest(){
        ConstraintViolationException exception = mock(ConstraintViolationException.class);
        
        response = exceptionErros.notValidConstraintError(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void notValidValueErrorTest(){
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);
        doReturn("Teste").when(exception).getName();        
        
        response = exceptionErros.notValidValueError(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void dataIntegrityViolationErrorTest(){
        DataIntegrityViolationException exception = mock(DataIntegrityViolationException.class);

        response = exceptionErros.dataIntegrityViolationError(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void notDataBaseConnectionErrorTest(){
        CannotCreateTransactionException exception = mock(CannotCreateTransactionException.class);

        response = exceptionErros.notDataBaseConnectionError(exception);
        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
    }

    @Test
    void businessErrorTest(){
        BusinessException exception = new BusinessException("Teste Mensagem!", HttpStatus.BAD_REQUEST, "Teste");
        response = exceptionErros.businessError(exception);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        exception = new BusinessException("Teste Mensagem!", HttpStatus.UNAUTHORIZED, "Teste");
        response = exceptionErros.businessError(exception);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
