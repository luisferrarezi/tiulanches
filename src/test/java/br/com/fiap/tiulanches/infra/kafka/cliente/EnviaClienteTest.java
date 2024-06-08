package br.com.fiap.tiulanches.infra.kafka.cliente;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.cliente.ClienteEvent;
import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.utils.cliente.ClientePadrao;

class EnviaClienteTest {
    private EnviaCliente enviaCliente;

    @Mock
    KafkaTemplate<String, Object> kafka;

    @BeforeEach
    @SuppressWarnings("unchecked")    
    void beforeEach(){
        kafka = Mockito.mock(KafkaTemplate.class);
        enviaCliente = new EnviaCliente(kafka);
    }

    @Test
    void constructorEnviaProdutoTest(){
        enviaCliente = new EnviaCliente(kafka);

        assertNotEquals(null, enviaCliente);
    }

    @Test
    void enviaMensagemTest(){
        ClientePadrao cliente = new ClientePadrao();

        ClienteDto clienteDto;
        clienteDto = new ClienteDto(cliente.createClient());

        when(kafka.send(anyString(), any(ClienteEvent.class))).thenReturn(null);
        assertDoesNotThrow(()->enviaCliente.enviaMensagem(EventoEnum.CREATE, clienteDto));
    }
}
