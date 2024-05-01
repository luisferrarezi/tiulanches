package br.com.fiap.tiulanches.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.tiulanches.adapter.message.produto.ProdutoMessage;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoRepository;
import br.com.fiap.tiulanches.core.entity.produto.Produto;
import br.com.fiap.tiulanches.core.service.ProdutoService;
import br.com.fiap.tiulanches.utils.produto.ProdutoPadrao;

class ProdutoServiceBDDTest {

    private ProdutoService produtoService;
    private ProdutoDto produtoDtoTeste;
    private ProdutoPadrao produtoPadrao;

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
        produtoService = new ProdutoService(produtoRepository, produtoMessage);
    }

    @AfterEach
    void afterEach() throws Exception {
      openMocks.close();
    }

    @Test
    void testCadastrar() {
        given(produtoRepository.save(any(Produto.class))).willReturn(produtoPadrao.createProduto());

        ProdutoDto produtoDtoRes = produtoService.cadastrar(produtoDtoTeste);
        assertEquals(0L, produtoDtoRes.idProduto());
        assertEquals(produtoDtoTeste.categoria(), produtoDtoRes.categoria());
        assertEquals(produtoDtoTeste.nome(), produtoDtoRes.nome());
        assertEquals(produtoDtoTeste.descricao(), produtoDtoRes.descricao());
        assertEquals(produtoDtoTeste.preco(), produtoDtoRes.preco());
        assertEquals(produtoDtoTeste.tempoPreparo(), produtoDtoRes.tempoPreparo());
        assertEquals(produtoDtoTeste.imagem(), produtoDtoRes.imagem());
    }
}
