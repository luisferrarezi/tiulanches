package br.com.fiap.tiulanches.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import br.com.fiap.tiulanches.adapter.controller.ProdutoController;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.enums.Categoria;
import br.com.fiap.tiulanches.core.exception.ExceptionErros;
import br.com.fiap.tiulanches.utils.Utils;
import br.com.fiap.tiulanches.utils.produto.ProdutoEnum;
import br.com.fiap.tiulanches.utils.produto.ProdutoPadrao;

class ProdutoApiTest {
    private MockMvc mockMvc;
    private ProdutoPadrao produtoPadrao;
    private Utils utils;

    private final Long idProdutoPadrao = (Long) ProdutoEnum.ID_PRODUTO.getValor();

    @Mock
    ProdutoController controller;

    AutoCloseable openMocks;

    @BeforeEach
    void beforeEach(){
        utils = new Utils();

        produtoPadrao = new ProdutoPadrao();

        openMocks = MockitoAnnotations.openMocks(this);

        ProdutoApi api = new ProdutoApi(controller);
        
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
        ProdutoDto produtoDto = produtoPadrao.createProdutoDto();

        when(controller.alterar(anyLong(), any(ProdutoDto.class)))
            .thenAnswer(i -> i.getArgument(1));

        mockMvc.perform(put("/produtos/{id}", idProdutoPadrao)
                .contentType(MediaType.APPLICATION_JSON)
                .content(utils.asJsonString(produtoDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idProduto").value(produtoDto.idProduto()))
            .andExpect(jsonPath("$.categoria").value(produtoDto.categoria().toString()))
            .andExpect(jsonPath("$.nome").value(produtoDto.nome()))
            .andExpect(jsonPath("$.descricao").value(produtoDto.descricao()))
            .andExpect(jsonPath("$.preco").value(produtoDto.preco()))
            .andExpect(jsonPath("$.tempoPreparo").value(produtoDto.tempoPreparo()))
            .andExpect(jsonPath("$.imagem").value(produtoDto.imagem()));
    }

    @Test
    void testCadastrar() throws Exception {
        ProdutoDto produtoDto = produtoPadrao.createProdutoDto();

        when(controller.cadastrar(any(ProdutoDto.class)))
            .thenAnswer(i -> i.getArgument(0));
  
        mockMvc.perform(post("/produtos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(utils.asJsonString(produtoDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testConsultar() throws Exception {
        ProdutoDto produtoDto = produtoPadrao.createProdutoDto();
        Page<ProdutoDto> page = new PageImpl<>(Collections.singletonList(
            produtoDto
        ));

        when(controller.consultaPaginada(any(Pageable.class))).thenReturn(page);
        mockMvc.perform(get("/produtos")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testConsultarByCategoria() throws Exception {
        ProdutoDto produtoDto = produtoPadrao.createProdutoDto();
        List<ProdutoDto> listProduto = new ArrayList<>(Collections.singletonList(
            produtoDto
        ));

        when(controller.consultaByCategoria(any(Categoria.class))).thenReturn(listProduto);
        mockMvc.perform(get("/categoria/{categoria}", Categoria.ACOMPANHAMENTO)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDetalhar() throws Exception {
        ProdutoDto produtoDto = produtoPadrao.createProdutoDto();

        when(controller.detalhar(anyLong())).thenReturn(produtoDto);
        mockMvc.perform(get("/produtos/{id}", produtoDto.idProduto())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProduto").value(produtoDto.idProduto()))
                .andExpect(jsonPath("$.categoria").value(produtoDto.categoria().toString()))
                .andExpect(jsonPath("$.nome").value(produtoDto.nome()))
                .andExpect(jsonPath("$.descricao").value(produtoDto.descricao()))
                .andExpect(jsonPath("$.preco").value(produtoDto.preco()))
                .andExpect(jsonPath("$.tempoPreparo").value(produtoDto.tempoPreparo()))
                .andExpect(jsonPath("$.imagem").value(produtoDto.imagem()));
    }

    @Test
    void testExcluir() throws Exception {
        doNothing().when(controller).excluir(anyLong());

        mockMvc.perform(delete("/produtos/{id}", idProdutoPadrao.longValue()))
            .andExpect(status().isNoContent());
    }
}
