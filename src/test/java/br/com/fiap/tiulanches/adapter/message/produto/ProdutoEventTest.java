package br.com.fiap.tiulanches.adapter.message.produto;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;

class ProdutoEventTest {

    private ProdutoEvent produtoEvent;
    
    @BeforeEach
    void beforeEach(){
        this.produtoEvent = new ProdutoEvent();
    }

    @Test
    void createTest(){
        produtoEvent.setProdutoDto(null);        
        produtoEvent.setEvento(EventoEnum.CREATE);

        assertEquals(EventoEnum.CREATE, produtoEvent.getEvento());
        assertEquals(null, produtoEvent.getProdutoDto());
    }

    @Test
    void constructorAllArgumentsTest(){
        produtoEvent = new ProdutoEvent(EventoEnum.CREATE, null);

        assertEquals(EventoEnum.CREATE, produtoEvent.getEvento());
    }

    @Test
    void equalsTest(){
        produtoEvent = new ProdutoEvent(EventoEnum.CREATE, null);
        ProdutoEvent produtoEvent2 = new ProdutoEvent(EventoEnum.CREATE, null);

        assertDoesNotThrow(()->produtoEvent.equals(produtoEvent2));
    }

    @Test
    void hashCodeTest(){
        produtoEvent = new ProdutoEvent(EventoEnum.CREATE, null);

        assertDoesNotThrow(()->produtoEvent.hashCode());
    }

    @Test
    void toStringTest(){
        produtoEvent = new ProdutoEvent(EventoEnum.CREATE, null);

        assertDoesNotThrow(()->produtoEvent.toString());
    }
}
