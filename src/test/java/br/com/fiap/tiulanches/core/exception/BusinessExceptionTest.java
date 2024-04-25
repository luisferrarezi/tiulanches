package br.com.fiap.tiulanches.core.exception;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class BusinessExceptionTest {

    private BusinessException exception;

    @Test
    void businessExceptionNoBodyTest(){
        exception = new BusinessException("Teste Mensagem!", HttpStatus.BAD_REQUEST);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(null, exception.getBody());
    }

    @Test
    void businessExceptionBodyTest(){
        exception = new BusinessException("Teste Mensagem!", HttpStatus.BAD_REQUEST, "Teste");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals("Teste", exception.getBody());
    }
}
