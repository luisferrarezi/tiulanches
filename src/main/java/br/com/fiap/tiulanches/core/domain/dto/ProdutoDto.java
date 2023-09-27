package br.com.fiap.tiulanches.core.domain.dto;

import java.math.BigDecimal;

import br.com.fiap.tiulanches.core.domain.entities.Produto;
import br.com.fiap.tiulanches.core.domain.enums.Categoria;

public record ProdutoDto(long idProduto,
						 Categoria categoria,
						 String nome,
						 String descricao,
						 BigDecimal preco,
						 int tempoPreparo,
						 String imagem) {
	public ProdutoDto(Produto produto) {
		this(produto.getIdProduto(), 
			 produto.getCategoria(), 
			 produto.getNome(), 
			 produto.getDescricao(), 
			 produto.getPreco(), 
			 produto.getTempoPreparo(), 
			 produto.getImagem());
	}	
}
