package br.com.fiap.tiulanches.core.entity.produto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.enums.Categoria;

class ProdutoTest {
    
    private ProdutoDto produtoDto;
    private Produto produto;

    private static final long ID_PRODUTO = 10L;
    private static final String NOME = "Teste";
    private static final String DESCRICAO = "Teste";
    private static final BigDecimal PRECO = BigDecimal.valueOf(11.20);
    private static final int TEMPO_PREPARO = 10;
    private static final String IMAGEM = "Teste";

    @Test
    void constructorAllArgumentsTest(){
        produto = new Produto(ID_PRODUTO, Categoria.ACOMPANHAMENTO, NOME, DESCRICAO, PRECO, TEMPO_PREPARO, IMAGEM, 0);
        
        assertEquals(ID_PRODUTO, produto.getIdProduto());
        assertEquals(Categoria.ACOMPANHAMENTO, produto.getCategoria());
        assertEquals(NOME, produto.getNome());
        assertEquals(DESCRICAO, produto.getDescricao());
        assertEquals(PRECO, produto.getPreco());
        assertEquals(TEMPO_PREPARO, produto.getTempoPreparo());
        assertEquals(IMAGEM, produto.getImagem());
        assertEquals(0, produto.getPedidoVinculado());
    }

    @Test
    void constructorByProdutoTest(){
        Produto produto = new Produto();

        produto.setIdProduto(ID_PRODUTO);
        produto.setCategoria(Categoria.ACOMPANHAMENTO);
        produto.setNome(NOME);
        produto.setDescricao(DESCRICAO);
        produto.setPreco(PRECO);
        produto.setTempoPreparo(TEMPO_PREPARO);
        produto.setImagem(IMAGEM);
        produto.setPedidoVinculado(0);

        assertEquals(ID_PRODUTO, produto.getIdProduto());
        assertEquals(Categoria.ACOMPANHAMENTO, produto.getCategoria());
        assertEquals(NOME, produto.getNome());
        assertEquals(DESCRICAO, produto.getDescricao());
        assertEquals(PRECO, produto.getPreco());
        assertEquals(TEMPO_PREPARO, produto.getTempoPreparo());
        assertEquals(IMAGEM, produto.getImagem());
        assertEquals(0, produto.getPedidoVinculado());
    }
    
    @Test
    void equalsTest(){
        produto = new Produto(ID_PRODUTO, Categoria.ACOMPANHAMENTO, NOME, DESCRICAO, PRECO, TEMPO_PREPARO, IMAGEM, 0);
        Produto produto2 = new Produto(ID_PRODUTO, Categoria.ACOMPANHAMENTO, NOME, DESCRICAO, PRECO, TEMPO_PREPARO, IMAGEM, 0);

        assertDoesNotThrow(()->produto.equals(produto2));
    }

    @Test
    void hashCodeTest(){
        produto = new Produto(ID_PRODUTO, Categoria.ACOMPANHAMENTO, NOME, DESCRICAO, PRECO, TEMPO_PREPARO, IMAGEM, 0);

        assertDoesNotThrow(()->produto.hashCode());
    }

    @Test
    void produtoIsPossuiPedidoTest(){
        produto = new Produto(ID_PRODUTO, Categoria.ACOMPANHAMENTO, NOME, DESCRICAO, PRECO, TEMPO_PREPARO, IMAGEM, 1);

        assertTrue(produto.isPossuiPedido());
    }

    @Test
    void produtoIsNotPossuiPedidoTest(){
        produto = new Produto(ID_PRODUTO, Categoria.ACOMPANHAMENTO, NOME, DESCRICAO, PRECO, TEMPO_PREPARO, IMAGEM, 0);

        assertFalse(produto.isPossuiPedido());
    }    

    @Test
    void produtoRegistrarTest(){
        produtoDto = new ProdutoDto(ID_PRODUTO, Categoria.ACOMPANHAMENTO, NOME, DESCRICAO, PRECO, TEMPO_PREPARO, IMAGEM);

        produto = new Produto();
        produto.registrar(produtoDto);
        
        assertEquals(Categoria.ACOMPANHAMENTO, produto.getCategoria());
        assertEquals(NOME, produto.getNome());
        assertEquals(DESCRICAO, produto.getDescricao());
        assertEquals(PRECO, produto.getPreco());
        assertEquals(TEMPO_PREPARO, produto.getTempoPreparo());
        assertEquals(IMAGEM, produto.getImagem());
    }    
   
    @Test
    void produtoRegistrarNullTest(){
        produtoDto = new ProdutoDto(ID_PRODUTO, null, null, null, null, 0, null);

        produto = new Produto();
        produto.registrar(produtoDto);
        
        assertEquals(null, produto.getCategoria());
        assertEquals(null, produto.getNome());
        assertEquals(null, produto.getDescricao());
        assertEquals(null, produto.getPreco());
        assertEquals(0, produto.getTempoPreparo());
        assertEquals(null, produto.getImagem());
    }            
}
