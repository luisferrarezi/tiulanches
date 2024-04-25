package br.com.fiap.tiulanches.adapter.repository.cliente;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.core.enums.Logado;
import br.com.fiap.tiulanches.utils.cliente.ClienteEnum;
import br.com.fiap.tiulanches.utils.cliente.ClientePadrao;

class ClienteDtoTest {
    private ClientePadrao clientePadrao;
    private ClienteDto clienteDto;

    @BeforeEach
    void beforeEach(){
        clientePadrao = new ClientePadrao();
    }

    @Test
    void constructorAllArgumentsTest(){
        clienteDto = clientePadrao.createClientDto();
        assertEquals(ClienteEnum.CPF.getValor(), clienteDto.cpf());
        assertEquals(ClienteEnum.NOME.getValor(), clienteDto.nome());
        assertEquals(ClienteEnum.EMAIL.getValor(), clienteDto.email());
        assertEquals(Logado.NAO, clienteDto.logado());
    }

    @Test
    void constructorByClienteTest(){
        clienteDto = new ClienteDto(clientePadrao.createClient());
        assertEquals(ClienteEnum.CPF.getValor(), clienteDto.cpf());
        assertEquals(ClienteEnum.NOME.getValor(), clienteDto.nome());
        assertEquals(ClienteEnum.EMAIL.getValor(), clienteDto.email());
        assertEquals(Logado.NAO, clienteDto.logado());
    }    
}
