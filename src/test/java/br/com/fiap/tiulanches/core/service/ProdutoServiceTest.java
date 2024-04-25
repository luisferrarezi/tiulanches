package br.com.fiap.tiulanches.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.produto.ProdutoMessage;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoRepository;
import br.com.fiap.tiulanches.core.entity.produto.Produto;
import br.com.fiap.tiulanches.core.enums.Categoria;
import br.com.fiap.tiulanches.core.exception.BusinessException;
import br.com.fiap.tiulanches.utils.produto.ProdutoEnum;
import br.com.fiap.tiulanches.utils.produto.ProdutoPadrao;

class ProdutoServiceTest {
    private ProdutoService produtoService;
    private Optional<Produto> produtoTeste;
    private ProdutoDto produtoDtoTeste;
    private ProdutoPadrao produtoPadrao;

    private final Long idProdutoPadrao = (Long) ProdutoEnum.ID_PRODUTO.getValor();

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoMessage produtoMessage;

    AutoCloseable openMocks;

    @BeforeEach
    void beforeEach(){
        openMocks = MockitoAnnotations.openMocks(this);
        
        produtoPadrao = new ProdutoPadrao();
        produtoDtoTeste = produtoPadrao.createProdutoDto();
        produtoTeste = Optional.ofNullable(produtoPadrao.createProduto());
        produtoService = new ProdutoService(produtoRepository, produtoMessage);
    }

    @AfterEach
    void afterEach() throws Exception {
      openMocks.close();
    }

    @Test
    void testAlterar() {
        ProdutoDto produtoDto = new ProdutoDto(idProdutoPadrao, Categoria.BEBIDA, "Abacate", "Abacate", BigDecimal.valueOf(10), 1, "IMAGEM");
        
        when(produtoRepository.findById(anyLong())).thenReturn(produtoTeste);
        doNothing().when(produtoMessage).enviaMensagem(any(EventoEnum.class), any(ProdutoDto.class));
        
        ProdutoDto produtoDtoRes = produtoService.alterar(idProdutoPadrao, produtoDto);
        assertEquals(idProdutoPadrao.longValue(), produtoDtoRes.idProduto());
        assertEquals(produtoDto.categoria(), produtoDtoRes.categoria());
        assertEquals(produtoDto.nome(), produtoDtoRes.nome());
        assertEquals(produtoDto.descricao(), produtoDtoRes.descricao());
        assertEquals(produtoDto.preco(), produtoDtoRes.preco());
        assertEquals(produtoDto.tempoPreparo(), produtoDtoRes.tempoPreparo());
        assertEquals(produtoDto.imagem(), produtoDtoRes.imagem());
    }

    @Test
    void testCadastrar() {
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoPadrao.createProduto());

        ProdutoDto produtoDtoRes = produtoService.cadastrar(produtoDtoTeste);
        assertEquals(0L, produtoDtoRes.idProduto());
        assertEquals(produtoDtoTeste.categoria(), produtoDtoRes.categoria());
        assertEquals(produtoDtoTeste.nome(), produtoDtoRes.nome());
        assertEquals(produtoDtoTeste.descricao(), produtoDtoRes.descricao());
        assertEquals(produtoDtoTeste.preco(), produtoDtoRes.preco());
        assertEquals(produtoDtoTeste.tempoPreparo(), produtoDtoRes.tempoPreparo());
        assertEquals(produtoDtoTeste.imagem(), produtoDtoRes.imagem());
    }

    @Test
    void testConsultaByCategoria() {
        List<Produto> list = new ArrayList<>(Collections.emptyList());

        when(produtoRepository.findByCategoria(any(Categoria.class))).thenReturn(list);
        List<ProdutoDto> produtos = produtoService.consultaByCategoria(Categoria.ACOMPANHAMENTO);
        assertTrue(produtos.isEmpty());
    }

    @Test
    void testConsultaPaginada() {
        Page<Produto> page = new PageImpl<>(Collections.emptyList());

        when(produtoRepository.findAll(any(Pageable.class))).thenReturn(page);
        Page<ProdutoDto> produtos = produtoService.consultaPaginada(Pageable.unpaged());
        assertTrue(produtos.isEmpty());
    }

    @Test
    void testDetalhar() {
        when(produtoRepository.findById(anyLong())).thenReturn(produtoTeste);
        ProdutoDto produtoDto = produtoService.detalhar(idProdutoPadrao);

        assertEquals(produtoDtoTeste.idProduto(), produtoDto.idProduto());
        assertEquals(produtoDtoTeste.categoria(), produtoDto.categoria());
        assertEquals(produtoDtoTeste.nome(), produtoDto.nome());
        assertEquals(produtoDtoTeste.descricao(), produtoDto.descricao());
        assertEquals(produtoDtoTeste.preco(), produtoDto.preco());
        assertEquals(produtoDtoTeste.tempoPreparo(), produtoDto.tempoPreparo());
        assertEquals(produtoDtoTeste.imagem(), produtoDto.imagem());
    }

    @Test
    void testExcluir() {
        when(produtoRepository.findById(anyLong())).thenReturn(produtoTeste);
        doNothing().when(produtoRepository).deleteById(anyLong());
        doNothing().when(produtoMessage).enviaMensagem(any(EventoEnum.class), any(ProdutoDto.class));
        assertDoesNotThrow(()-> produtoService.excluir(idProdutoPadrao));

        produtoTeste.get().setPedidoVinculado(1);
        when(produtoRepository.findById(anyLong())).thenReturn(produtoTeste);
        BusinessException exception = assertThrows(BusinessException.class, ()-> produtoService.excluir(idProdutoPadrao));
        assertEquals("Produto já utilizado em pedido, não pode ser excluído!", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
