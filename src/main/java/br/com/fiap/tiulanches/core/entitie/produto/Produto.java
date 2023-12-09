package br.com.fiap.tiulanches.core.entitie.produto;

import java.math.BigDecimal;

import br.com.fiap.tiulanches.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.enums.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Produto")
@Table(name = "PRODUTOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idProduto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Código do produto após ser criado", example = "1", required = true)
	private long idProduto;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Schema(implementation = Categoria.class, description = "Categoria do produto", example = "LANCHE", required = true)	
	private Categoria categoria;
	
	@NotBlank
	@Size(max=30)
	@Schema(description = "Nome do produto", example = "X-Tudo", required = true, maxLength = 30)	
	private String nome;
	
	@NotBlank
	@Size(max=200)
	@Schema(description = "Descrição do produto", 
    		example = "pão, 2 carnes, queijo, presunto, bacon, ovo, alface, tomate, milho e batata.", 
    		required = true, 
    		maxLength = 200)	
	private String descricao;
	
	@NotNull
	@Positive
	@Schema(description = "Preço do produto", example = "19.32", required = true)	
	private BigDecimal preco;
	
	@Schema(description = "Tempo em minutos necessário para preparar todo o produto.", example = "35")
	private int tempoPreparo;
	
	@Size(max=400)
	@Schema(description = "Caminho onde a imagem se encontra disponibilizada", 
    		example = "https://img.freepik.com/fotos-gratis/vista-frontal-deliciosas-batatas-fritas-com-cheeseburgers-em-fundo-escuro-lanche-prato-fast-food-torrada-hamburguer-jantar_140725-158687.jpg?w=2000", 
    		maxLength = 400)	
	private String imagem;
	
	public void atualizar(ProdutoDto produto) {
		validaCategoria(produto.categoria());		
		validaNome(produto.nome());
		validaDescricao(produto.descricao());		
		validaPreco(produto.preco());		
		validaTempoPreparo(produto.tempoPreparo());
		validaImagem(produto.imagem());
	}
	
	public void cadastrar(ProdutoDto produto) {
		validaCategoria(produto.categoria());		
		validaNome(produto.nome());
		validaDescricao(produto.descricao());		
		validaPreco(produto.preco());		
		validaTempoPreparo(produto.tempoPreparo());
		validaImagem(produto.imagem());
	}	
	
	private void validaCategoria(Categoria categoria) {
		if (categoria != null) {
			this.categoria = categoria;
		}		
	}
	
	private void validaNome(String nome) {
		if (nome != null) {
			this.nome = nome;
		}		
	}
	
	private void validaDescricao(String descricao) {
		if (descricao != null) {
			this.descricao = descricao;
		}		
	}
	
	private void validaPreco(BigDecimal preco) {
		if (preco != null) {
			this.preco = preco;
		}		
	}
	
	private void validaTempoPreparo(int tempoPreparo) {
		if (tempoPreparo > 0) {
			this.tempoPreparo = tempoPreparo;
		}			
	}
	
	private void validaImagem(String imagem) {
		if (imagem != null) {
			this.imagem = imagem;
		}
	}
}
