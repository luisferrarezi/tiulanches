package br.com.fiap.tiulanches.adapter.repository.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.core.entity.produto.Produto;
import br.com.fiap.tiulanches.core.enums.Categoria;

class ProdutoDtoTest {

    private ProdutoDto produtoDto;

    private static final long ID_PRODUTO = 10L;
    private static final String NOME = "Teste";
    private static final String DESCRICAO = "Teste";
    private static final BigDecimal PRECO = BigDecimal.valueOf(11.20);
    private static final int TEMPO_PREPARO = 10;
    private static final String IMAGEM = "Teste";

    @Test
    void constructorAllArgumentsTest(){
        produtoDto = new ProdutoDto(ID_PRODUTO, Categoria.ACOMPANHAMENTO, NOME, DESCRICAO, PRECO, TEMPO_PREPARO, IMAGEM);
        
        assertEquals(ID_PRODUTO, produtoDto.idProduto());
        assertEquals(Categoria.ACOMPANHAMENTO, produtoDto.categoria());
        assertEquals(NOME, produtoDto.nome());
        assertEquals(DESCRICAO, produtoDto.descricao());
        assertEquals(PRECO, produtoDto.preco());
        assertEquals(TEMPO_PREPARO, produtoDto.tempoPreparo());
        assertEquals(IMAGEM, produtoDto.imagem());
    }

    @Test
    void constructorByProdutoTest(){
        Produto produto = new Produto(ID_PRODUTO, Categoria.ACOMPANHAMENTO, NOME, DESCRICAO, PRECO, TEMPO_PREPARO, IMAGEM, 0);
        produtoDto = new ProdutoDto(produto);

        assertEquals(ID_PRODUTO, produtoDto.idProduto());
        assertEquals(Categoria.ACOMPANHAMENTO, produtoDto.categoria());
        assertEquals(NOME, produtoDto.nome());
        assertEquals(DESCRICAO, produtoDto.descricao());
        assertEquals(PRECO, produtoDto.preco());
        assertEquals(TEMPO_PREPARO, produtoDto.tempoPreparo());
        assertEquals(IMAGEM, produtoDto.imagem());
    }    
}
