package br.com.fiap.tiulanches.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.cliente.ClienteMessage;
import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteRepository;
import br.com.fiap.tiulanches.core.entity.cliente.Cliente;
import br.com.fiap.tiulanches.core.exception.BusinessException;
import br.com.fiap.tiulanches.utils.cliente.ClienteEnum;
import br.com.fiap.tiulanches.utils.cliente.ClientePadrao;

class ClienteServiceTest {    
    private ClienteService clienteService;    
    private Optional<Cliente> clienteTest;
    private ClienteDto clienteDtoTest;    
    
    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMessage message;

    @BeforeEach
    void beforeEach(){
        ClientePadrao clientePadrao;
        MockitoAnnotations.openMocks(this);

        clientePadrao = new ClientePadrao();
        clienteDtoTest = clientePadrao.createClientDto();
        clienteTest = Optional.ofNullable(clientePadrao.createClient());
        clienteService = new ClienteService(clienteRepository, message);
    }

    @Test
    void testAlterar() {        
        ClienteDto clienteDto = new ClienteDto(ClienteEnum.CPF.getValor(), "Maria", "maria@gmail.com", "1", "2", "3", "4", "5", "6", "7");
        
        when(clienteRepository.findById(anyString())).thenReturn(clienteTest);
        doNothing().when(message).enviaMensagem(any(EventoEnum.class), any(ClienteDto.class));
        
        ClienteDto clienteDtoRes = clienteService.alterar(ClienteEnum.CPF.getValor(), clienteDto);
        assertEquals(ClienteEnum.CPF.getValor(), clienteDtoRes.cpf());
        assertEquals(clienteDto.email(), clienteDtoRes.email());
        assertEquals(clienteDto.nome(), clienteDtoRes.nome());        
    }

    
    @Test
    void testCadastrar() {
        ClienteDto clienteDto = new ClienteDto(null, "Maria", "maria@gmail.com", "1", "2", "3", "4", "5", "6", "7");

        BusinessException exception = assertThrows(BusinessException.class, ()-> clienteService.cadastrar(clienteDto));
        assertEquals("CPF não informado!", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());

        when(clienteRepository.findById(anyString())).thenReturn(Optional.of(new Cliente()));
        exception = assertThrows(BusinessException.class, ()-> clienteService.cadastrar(clienteDtoTest));
        assertEquals("Cliente já cadastrado", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());

        when(clienteRepository.findById(anyString())).thenReturn(Optional.empty());
        ClienteDto clienteDtoRes = clienteService.cadastrar(clienteDtoTest);
        assertEquals(clienteDtoTest.cpf(), clienteDtoRes.cpf());
        assertEquals(clienteDtoTest.email(), clienteDtoRes.email());
        assertEquals(clienteDtoTest.nome(), clienteDtoRes.nome());        
        assertEquals(clienteDtoTest.endereco(), clienteDtoRes.endereco());        
        assertEquals(clienteDtoTest.numero(), clienteDtoRes.numero());        
        assertEquals(clienteDtoTest.bairro(), clienteDtoRes.bairro());        
        assertEquals(clienteDtoTest.cidade(), clienteDtoRes.cidade());        
        assertEquals(clienteDtoTest.estado(), clienteDtoRes.estado());        
        assertEquals(clienteDtoTest.cep(), clienteDtoRes.cep());        
        assertEquals(clienteDtoTest.telefone(), clienteDtoRes.telefone());        
    }

    @Test
    void testConsultaPaginada() {
        Page<Cliente> page = new PageImpl<>(Collections.emptyList());

        when(clienteRepository.findAll(any(Pageable.class))).thenReturn(page);
        Page<ClienteDto> clientes = clienteService.consultaPaginada(Pageable.unpaged());
        assertTrue(clientes.isEmpty());
    }

    @Test
    void testDetalhar() {
        when(clienteRepository.findById(anyString())).thenReturn(clienteTest);
        ClienteDto clienteDto = clienteService.detalhar(ClienteEnum.CPF.getValor());

        assertEquals(clienteDtoTest.cpf(), clienteDto.cpf());
        assertEquals(clienteDtoTest.email(), clienteDto.email());
        assertEquals(clienteDtoTest.nome(), clienteDto.nome());        
        assertEquals(clienteDtoTest.endereco(), clienteDto.endereco());        
        assertEquals(clienteDtoTest.numero(), clienteDto.numero());        
        assertEquals(clienteDtoTest.bairro(), clienteDto.bairro());        
        assertEquals(clienteDtoTest.cidade(), clienteDto.cidade());        
        assertEquals(clienteDtoTest.estado(), clienteDto.estado());        
        assertEquals(clienteDtoTest.cep(), clienteDto.cep());        
        assertEquals(clienteDtoTest.telefone(), clienteDto.telefone());        
    }

    @Test
    void testExcluir() {
        when(clienteRepository.findById(anyString())).thenReturn(clienteTest);
        doNothing().when(clienteRepository).deleteById(anyString());
        doNothing().when(message).enviaMensagem(any(EventoEnum.class), any(ClienteDto.class));
        assertDoesNotThrow(()-> clienteService.excluir(ClienteEnum.CPF.getValor()));
    }
}
