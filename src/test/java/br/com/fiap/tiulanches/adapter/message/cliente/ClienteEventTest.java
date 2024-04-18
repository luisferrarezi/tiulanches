package br.com.fiap.tiulanches.adapter.message.cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;

class ClienteEventTest {

    private ClienteEvent clienteEvent;
    
    @BeforeEach
    void beforeEach(){
        this.clienteEvent = new ClienteEvent();
    }

    @Test
    void createTest(){
        clienteEvent.setClienteDto(null);        
        clienteEvent.setEvento(EventoEnum.CREATE);

        assertEquals(EventoEnum.CREATE, clienteEvent.getEvento());
        assertEquals(null, clienteEvent.getClienteDto());
    }

    @Test
    void constructorAllArgumentsTest(){
        clienteEvent = new ClienteEvent(EventoEnum.CREATE, null);

        assertEquals(EventoEnum.CREATE, clienteEvent.getEvento());
    }    

    @Test
    void equalsTest(){
        clienteEvent = new ClienteEvent(EventoEnum.CREATE, null);
        ClienteEvent clienteEvent2 = new ClienteEvent(EventoEnum.CREATE, null);

        assertDoesNotThrow(()->clienteEvent.equals(clienteEvent2));
    }

    @Test
    void hashCodeTest(){
        clienteEvent = new ClienteEvent(EventoEnum.CREATE, null);        

        assertDoesNotThrow(()->clienteEvent.hashCode());
    }

    @Test
    void toStringTest(){
        clienteEvent = new ClienteEvent(EventoEnum.CREATE, null);        

        assertDoesNotThrow(()->clienteEvent.toString());
    }
}
