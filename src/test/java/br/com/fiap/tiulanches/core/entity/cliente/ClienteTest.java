package br.com.fiap.tiulanches.core.entity.cliente;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
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
        assertEquals(ClienteEnum.ENDERECO.getValor(), cliente.getEndereco());        
        assertEquals(ClienteEnum.NUMERO.getValor(), cliente.getNumero());        
        assertEquals(ClienteEnum.BAIRRO.getValor(), cliente.getBairro());        
        assertEquals(ClienteEnum.CIDADE.getValor(), cliente.getCidade());        
        assertEquals(ClienteEnum.ESTADO.getValor(), cliente.getEstado());        
        assertEquals(ClienteEnum.CEP.getValor(), cliente.getCep());        
        assertEquals(ClienteEnum.TELEFONE.getValor(), cliente.getTelefone());        
    }

    @Test
    void constructorNoArgumentsTest(){
        cliente = new Cliente();
        cliente.setCpf(ClienteEnum.CPF.getValor());
        cliente.setNome(ClienteEnum.NOME.getValor());
        cliente.setEmail(ClienteEnum.EMAIL.getValor());
        cliente.setEndereco(ClienteEnum.ENDERECO.getValor());
        cliente.setNumero(ClienteEnum.NUMERO.getValor());
        cliente.setBairro(ClienteEnum.BAIRRO.getValor());
        cliente.setCidade(ClienteEnum.CIDADE.getValor());
        cliente.setEstado(ClienteEnum.ESTADO.getValor());
        cliente.setCep(ClienteEnum.CEP.getValor());
        cliente.setTelefone(ClienteEnum.TELEFONE.getValor());

        assertEquals(ClienteEnum.CPF.getValor(), cliente.getCpf());
        assertEquals(ClienteEnum.NOME.getValor(), cliente.getNome());
        assertEquals(ClienteEnum.EMAIL.getValor(), cliente.getEmail());
        assertEquals(ClienteEnum.ENDERECO.getValor(), cliente.getEndereco());        
        assertEquals(ClienteEnum.NUMERO.getValor(), cliente.getNumero());        
        assertEquals(ClienteEnum.BAIRRO.getValor(), cliente.getBairro());        
        assertEquals(ClienteEnum.CIDADE.getValor(), cliente.getCidade());        
        assertEquals(ClienteEnum.ESTADO.getValor(), cliente.getEstado());        
        assertEquals(ClienteEnum.CEP.getValor(), cliente.getCep());        
        assertEquals(ClienteEnum.TELEFONE.getValor(), cliente.getTelefone());        
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
    void clienteAtualizarTest(){
        cliente = new Cliente();
        cliente.atualizar(clientePadrao.createClientDto());
        
        assertEquals(ClienteEnum.NOME.getValor(), cliente.getNome());
        assertEquals(ClienteEnum.EMAIL.getValor(), cliente.getEmail());
        assertEquals(ClienteEnum.ENDERECO.getValor(), cliente.getEndereco());        
        assertEquals(ClienteEnum.NUMERO.getValor(), cliente.getNumero());        
        assertEquals(ClienteEnum.BAIRRO.getValor(), cliente.getBairro());        
        assertEquals(ClienteEnum.CIDADE.getValor(), cliente.getCidade());        
        assertEquals(ClienteEnum.ESTADO.getValor(), cliente.getEstado());        
        assertEquals(ClienteEnum.CEP.getValor(), cliente.getCep());        
        assertEquals(ClienteEnum.TELEFONE.getValor(), cliente.getTelefone());        
    }    
    
    @Test
    void clienteCriarTest(){
        cliente = new Cliente();
        cliente.cadastrar(clientePadrao.createClientDto());
        
        assertEquals(ClienteEnum.CPF.getValor(), cliente.getCpf());
        assertEquals(ClienteEnum.NOME.getValor(), cliente.getNome());
        assertEquals(ClienteEnum.EMAIL.getValor(), cliente.getEmail());
        assertEquals(ClienteEnum.ENDERECO.getValor(), cliente.getEndereco());        
        assertEquals(ClienteEnum.NUMERO.getValor(), cliente.getNumero());        
        assertEquals(ClienteEnum.BAIRRO.getValor(), cliente.getBairro());        
        assertEquals(ClienteEnum.CIDADE.getValor(), cliente.getCidade());        
        assertEquals(ClienteEnum.ESTADO.getValor(), cliente.getEstado());        
        assertEquals(ClienteEnum.CEP.getValor(), cliente.getCep());        
        assertEquals(ClienteEnum.TELEFONE.getValor(), cliente.getTelefone());        
    }    
    
    @Test
    void clienteAtualizarNullTest(){
        ClienteDto clienteDto = new ClienteDto(ClienteEnum.NOME.getValor(), null, null, null, null, null, null, null, null, null);

        cliente = new Cliente();
        cliente.atualizar(clienteDto);
        
        assertEquals(null, cliente.getNome());
        assertEquals(null, cliente.getEmail());
        assertEquals(null, cliente.getEndereco());        
        assertEquals(null, cliente.getNumero());        
        assertEquals(null, cliente.getBairro());        
        assertEquals(null, cliente.getCidade());        
        assertEquals(null, cliente.getEstado());        
        assertEquals(null, cliente.getCep());        
        assertEquals(null, cliente.getTelefone());                
    }        
}
