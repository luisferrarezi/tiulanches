package br.com.fiap.tiulanches.adapter.repository.cliente;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.core.entity.cliente.Cliente;
import br.com.fiap.tiulanches.core.enums.Logado;

class ClienteDtoTest {
    
    private ClienteDto clienteDto;
    private static final String CPF = "68330488004";
    private static final String NOME = "teste";
    private static final String EMAIL = "teste@teste.com";

    @Test
    void constructorAllArgumentsTest(){
        clienteDto = new ClienteDto(CPF, NOME, EMAIL, Logado.NAO);
        assertEquals(CPF, clienteDto.cpf());
        assertEquals(NOME, clienteDto.nome());
        assertEquals(EMAIL, clienteDto.email());
        assertEquals(Logado.NAO, clienteDto.logado());
    }

    @Test
    void constructorByClienteTest(){
        Cliente cliente = new Cliente(CPF, NOME, EMAIL, Logado.NAO, 0);
        
        clienteDto = new ClienteDto(cliente);
        assertEquals(CPF, clienteDto.cpf());
        assertEquals(NOME, clienteDto.nome());
        assertEquals(EMAIL, clienteDto.email());
        assertEquals(Logado.NAO, clienteDto.logado());
    }    
}
