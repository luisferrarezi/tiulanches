package br.com.fiap.tiulanches.core.domain.dto;

import java.math.BigDecimal;

import br.com.fiap.tiulanches.core.domain.entities.Produto;
import br.com.fiap.tiulanches.core.domain.enums.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

public record ProdutoDto(@Schema(description = "Código do produto após ser criado", example = "1", required = true)
						 long idProduto,
						 @Schema(implementation = Categoria.class, description = "Categoria do produto", example = "LANCHE", required = true)
						 Categoria categoria,
						 @Schema(description = "Nome do produto", example = "X-Tudo", required = true, maxLength = 30)
						 String nome,
						 @Schema(description = "Descrição do produto", 
						         example = "pão, 2 carnes, queijo, presunto, bacon, ovo, alface, tomate, milho e batata.", 
						         required = true, 
						         maxLength = 200)
						 String descricao,
						 @Schema(description = "Preço do produto", example = "19.00", required = true)
						 BigDecimal preco,
						 @Schema(description = "Tempo em minutos necessário para preparar todo o produto.", example = "35")
						 int tempoPreparo,
						 @Schema(description = "Caminho onde a imagem se encontra disponibilizada", 
						         example = "https://img.freepik.com/fotos-gratis/vista-frontal-deliciosas-batatas-fritas-com-cheeseburgers-em-fundo-escuro-lanche-prato-fast-food-torrada-hamburguer-jantar_140725-158687.jpg?w=2000", 
						         maxLength = 400)
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
