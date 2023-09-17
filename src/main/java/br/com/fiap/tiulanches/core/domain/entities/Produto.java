package br.com.fiap.tiulanches.core.domain.entities;

import java.math.BigDecimal;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUTOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	
	@Size(min=1)
	private int tempoPreparo;
	
	@Size(max=400)
	private String imagem;
}
