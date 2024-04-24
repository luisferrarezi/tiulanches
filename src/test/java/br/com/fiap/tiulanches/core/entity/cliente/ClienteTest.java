package br.com.fiap.tiulanches.core.entity.cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.core.enums.Logado;

class ClienteTest {

    private Cliente cliente;
    private static final String CPF = "68330488004";
    private static final String NOME = "teste";
    private static final String EMAIL = "teste@teste.com";

    @Test
    void constructorAllArgumentsTest(){
        cliente = new Cliente(CPF, NOME, EMAIL, Logado.NAO, 0);
        assertEquals(CPF, cliente.getCpf());
        assertEquals(NOME, cliente.getNome());
        assertEquals(EMAIL, cliente.getEmail());
        assertEquals(Logado.NAO, cliente.getLogado());
        assertEquals(0, cliente.getPedidoVinculado());        
    }

    @Test
    void constructorNoArgumentsTest(){
        cliente = new Cliente();
        cliente.setCpf(CPF);
        cliente.setNome(NOME);
        cliente.setEmail(EMAIL);
        cliente.setLogado(Logado.NAO);
        cliente.setPedidoVinculado(0);

        assertEquals(CPF, cliente.getCpf());
        assertEquals(NOME, cliente.getNome());
        assertEquals(EMAIL, cliente.getEmail());
        assertEquals(Logado.NAO, cliente.getLogado());
        assertEquals(0, cliente.getPedidoVinculado());        
    }    

    @Test
    void equalsTest(){
        cliente = new Cliente(CPF, NOME, EMAIL, Logado.NAO, 0);
        Cliente cliente2 = new Cliente(CPF, NOME, EMAIL, Logado.NAO, 0);

        assertDoesNotThrow(()->cliente.equals(cliente2));
    }

    @Test
    void hashCodeTest(){
        cliente = new Cliente(CPF, NOME, EMAIL, Logado.NAO, 0);

        assertDoesNotThrow(()->cliente.hashCode());
    }

    @Test
    void clienteIsLogadoTest(){
        cliente = new Cliente(CPF, NOME, EMAIL, Logado.SIM, 0);

        assertTrue(cliente.isLogado());
    }

    @Test
    void clienteIsNotLogadoTest(){
        cliente = new Cliente(CPF, NOME, EMAIL, Logado.NAO, 0);

        assertFalse(cliente.isLogado());
    }    

    @Test
    void clienteIsPossuiPedidoTest(){
        cliente = new Cliente(CPF, NOME, EMAIL, Logado.SIM, 1);

        assertTrue(cliente.isPossuiPedido());
    }

    @Test
    void clienteIsNotPossuiPedidoTest(){
        cliente = new Cliente(CPF, NOME, EMAIL, Logado.SIM, 0);

        assertFalse(cliente.isPossuiPedido());
    }    

    @Test
    void clienteAtualizarTest(){
        ClienteDto clienteDto = new ClienteDto(CPF, NOME, EMAIL, Logado.NAO);

        cliente = new Cliente();
        cliente.atualizar(clienteDto);
        
        assertEquals(NOME, cliente.getNome());
        assertEquals(EMAIL, cliente.getEmail());
    }    
    
    @Test
    void clienteCriarTest(){
        ClienteDto clienteDto = new ClienteDto(CPF, NOME, EMAIL, Logado.NAO);

        cliente = new Cliente();
        cliente.cadastrar(clienteDto);
        
        assertEquals(CPF, cliente.getCpf());
        assertEquals(NOME, cliente.getNome());
        assertEquals(EMAIL, cliente.getEmail());
        assertEquals(Logado.NAO, cliente.getLogado());
    }    
    
    @Test
    void clienteAtualizarNullTest(){
        ClienteDto clienteDto = new ClienteDto(CPF, null, null, Logado.NAO);

        cliente = new Cliente();
        cliente.atualizar(clienteDto);
        
        assertEquals(null, cliente.getNome());
        assertEquals(null, cliente.getEmail());
    }        
}
