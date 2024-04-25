package br.com.fiap.tiulanches.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.fiap.tiulanches.adapter.controller.ClienteController;
import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.core.exception.ExceptionErros;
import br.com.fiap.tiulanches.utils.Utils;
import br.com.fiap.tiulanches.utils.cliente.ClienteEnum;
import br.com.fiap.tiulanches.utils.cliente.ClientePadrao;

class ClienteApiTest {
    private MockMvc mockMvc;    
    private ClientePadrao clientePadrao;
    private Utils utils;
    
    @Mock
    ClienteController controller;

    AutoCloseable openMocks;

    @BeforeEach
    void beforeEach() {
        utils = new Utils();

        clientePadrao = new ClientePadrao();

        openMocks = MockitoAnnotations.openMocks(this);
        ClienteApi api = new ClienteApi(controller);
      
        mockMvc = MockMvcBuilders.standaloneSetup(api)
            .setControllerAdvice(new ExceptionErros())
            .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
            .build();
    }  

    @AfterEach
    void afterEach() throws Exception {
      openMocks.close();
    }

    @Test
    void testAlterar() throws Exception {
        ClienteDto clienteDto = clientePadrao.createClientDto();

        when(controller.alterar(anyString(), any(ClienteDto.class)))
            .thenAnswer(i -> i.getArgument(1));

        mockMvc.perform(put("/clientes/{cpf}", ClienteEnum.CPF.getValor())
                .contentType(MediaType.APPLICATION_JSON)
                .content(utils.asJsonString(clienteDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.cpf").value(clienteDto.cpf()))
            .andExpect(jsonPath("$.nome").value(clienteDto.nome()))
            .andExpect(jsonPath("$.email").value(clienteDto.email()));        
    }

    @Test
    void testCadastrar() throws Exception {
        ClienteDto clienteDto = clientePadrao.createClientDto();

        when(controller.cadastrar(any(ClienteDto.class)))
            .thenAnswer(i -> i.getArgument(0));
  
        mockMvc.perform(post("/clientes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(utils.asJsonString(clienteDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testConsultar() throws Exception {
        ClienteDto clienteDto = clientePadrao.createClientDto();
        Page<ClienteDto> page = new PageImpl<>(Collections.singletonList(
            clienteDto
        ));

        when(controller.consultaPaginada(any(Pageable.class))).thenReturn(page);
        mockMvc.perform(get("/clientes")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testDetalhar() throws Exception {
        ClienteDto clienteDto = clientePadrao.createClientDto();

        when(controller.detalhar(anyString())).thenReturn(clienteDto);
        mockMvc.perform(get("/clientes/{cpf}", clienteDto.cpf())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value(clienteDto.cpf()))
                .andExpect(jsonPath("$.nome").value(clienteDto.nome()))
                .andExpect(jsonPath("$.email").value(clienteDto.email()));
    }

    @Test
    void testExcluir() throws Exception {
        doNothing().when(controller).excluir(anyString());

        mockMvc.perform(delete("/clientes/{cpf}", ClienteEnum.CPF.getValor()))
            .andExpect(status().isNoContent());
    }
}
