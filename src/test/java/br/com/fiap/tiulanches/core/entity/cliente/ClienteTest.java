package br.com.fiap.tiulanches.core.entity.cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.core.enums.Logado;
import br.com.fiap.tiulanches.utils.cliente.ClienteEnum;
import br.com.fiap.tiulanches.utils.cliente.ClientePadrao;

class ClienteTest {

    private Cliente cliente;
    private ClientePadrao clientePadrao;

    @BeforeEach
    void beforeEach(){
        clientePadrao = new ClientePadrao();
    }

    @Test
    void constructorAllArgumentsTest(){
        cliente = clientePadrao.createClient();
        assertEquals(ClienteEnum.CPF.getValor(), cliente.getCpf());
        assertEquals(ClienteEnum.NOME.getValor(), cliente.getNome());
        assertEquals(ClienteEnum.EMAIL.getValor(), cliente.getEmail());
        assertEquals(Logado.NAO, cliente.getLogado());
        assertEquals(0, cliente.getPedidoVinculado());        
    }

    @Test
    void constructorNoArgumentsTest(){
        cliente = new Cliente();
        cliente.setCpf(ClienteEnum.CPF.getValor());
        cliente.setNome(ClienteEnum.NOME.getValor());
        cliente.setEmail(ClienteEnum.EMAIL.getValor());
        cliente.setLogado(Logado.NAO);
        cliente.setPedidoVinculado(0);

        assertEquals(ClienteEnum.CPF.getValor(), cliente.getCpf());
        assertEquals(ClienteEnum.NOME.getValor(), cliente.getNome());
        assertEquals(ClienteEnum.EMAIL.getValor(), cliente.getEmail());
        assertEquals(Logado.NAO, cliente.getLogado());
        assertEquals(0, cliente.getPedidoVinculado());        
    }    

    @Test
    void equalsTest(){
        cliente = clientePadrao.createClient();
        Cliente cliente2 = clientePadrao.createClient();

        assertDoesNotThrow(()->cliente.equals(cliente2));
    }

    @Test
    void hashCodeTest(){
        cliente = clientePadrao.createClient();        

        assertDoesNotThrow(()->cliente.hashCode());
    }

    @Test
    void clienteIsLogadoTest(){
        cliente = new Cliente(ClienteEnum.CPF.getValor(), 
                              ClienteEnum.NOME.getValor(), 
                              ClienteEnum.EMAIL.getValor(), 
                              Logado.SIM, 
                              0);

        assertTrue(cliente.isLogado());
    }

    @Test
    void clienteIsNotLogadoTest(){
        cliente = clientePadrao.createClient();

        assertFalse(cliente.isLogado());
    }    

    @Test
    void clienteIsPossuiPedidoTest(){
        cliente = new Cliente(ClienteEnum.CPF.getValor(), 
                              ClienteEnum.NOME.getValor(), 
                              ClienteEnum.EMAIL.getValor(), 
                              Logado.SIM, 
                              1);

        assertTrue(cliente.isPossuiPedido());
    }

    @Test
    void clienteIsNotPossuiPedidoTest(){
        cliente = clientePadrao.createClient();

        assertFalse(cliente.isPossuiPedido());
    }    

    @Test
    void clienteAtualizarTest(){
        cliente = new Cliente();
        cliente.atualizar(clientePadrao.createClientDto());
        
        assertEquals(ClienteEnum.NOME.getValor(), cliente.getNome());
        assertEquals(ClienteEnum.EMAIL.getValor(), cliente.getEmail());
    }    
    
    @Test
    void clienteCriarTest(){
        cliente = new Cliente();
        cliente.cadastrar(clientePadrao.createClientDto());
        
        assertEquals(ClienteEnum.CPF.getValor(), cliente.getCpf());
        assertEquals(ClienteEnum.NOME.getValor(), cliente.getNome());
        assertEquals(ClienteEnum.EMAIL.getValor(), cliente.getEmail());
        assertEquals(Logado.NAO, cliente.getLogado());
    }    
    
    @Test
    void clienteAtualizarNullTest(){
        ClienteDto clienteDto = new ClienteDto(ClienteEnum.NOME.getValor(), null, null, Logado.NAO);

        cliente = new Cliente();
        cliente.atualizar(clienteDto);
        
        assertEquals(null, cliente.getNome());
        assertEquals(null, cliente.getEmail());
    }        
}
