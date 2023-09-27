package br.com.fiap.tiulanches.core.domain.entities;

import java.math.BigDecimal;

import br.com.fiap.tiulanches.core.domain.dto.ProdutoDto;
import br.com.fiap.tiulanches.core.domain.enums.Categoria;
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
	private long idProduto;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Categoria categoria;
	
	@NotBlank
	@Size(max=30)
	private String nome;
	
	@NotBlank
	@Size(max=200)
	private String descricao;
	
	@NotNull
	@Positive
	private BigDecimal preco;
	
	private int tempoPreparo;
	
	@Size(max=400)
	private String imagem;
	
	public void atualizar(ProdutoDto produto) {
		if (produto.categoria() != null) {
			this.categoria = produto.categoria();
		}		
		
		if (produto.nome() != null) {
			this.nome = produto.nome();
		}		
		
		if (produto.descricao() != null) {
			this.descricao = produto.descricao();
		}		
		
		if (produto.preco() != null) {
			this.preco = produto.preco();
		}		
		
		if (produto.tempoPreparo() > 0) {
			this.tempoPreparo = produto.tempoPreparo();
		}		
		
		if (produto.imagem() != null) {
			this.imagem = produto.imagem();
		}
	}
	
	public void criar(ProdutoDto produto) {
		this.categoria = produto.categoria();
		this.nome = produto.nome();
		this.descricao = produto.descricao();
		this.preco = produto.preco();
		this.tempoPreparo = produto.tempoPreparo();
		this.imagem = produto.imagem();
	}	
}
