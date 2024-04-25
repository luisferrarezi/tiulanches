package br.com.fiap.tiulanches.utils.produto;

import java.math.BigDecimal;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.entity.produto.Produto;
import br.com.fiap.tiulanches.core.enums.Categoria;

public class ProdutoPadrao {

    public Produto createProduto(){
        return new Produto((Long) ProdutoEnum.ID_PRODUTO.getValor(), 
                           Categoria.ACOMPANHAMENTO, 
                           (String) ProdutoEnum.NOME.getValor(), 
                           (String) ProdutoEnum.DESCRICAO.getValor(), 
                           (BigDecimal) ProdutoEnum.PRECO.getValor(), 
                           (Integer) ProdutoEnum.TEMPO_PREPARO.getValor(), 
                           (String) ProdutoEnum.IMAGEM.getValor(), 
                           0);
    }

    public ProdutoDto createProdutoDto(){
        return new ProdutoDto((Long) ProdutoEnum.ID_PRODUTO.getValor(), 
                              Categoria.ACOMPANHAMENTO, 
                              (String) ProdutoEnum.NOME.getValor(), 
                              (String) ProdutoEnum.DESCRICAO.getValor(), 
                              (BigDecimal) ProdutoEnum.PRECO.getValor(), 
                              (Integer) ProdutoEnum.TEMPO_PREPARO.getValor(), 
                              (String) ProdutoEnum.IMAGEM.getValor());
    }    
}
