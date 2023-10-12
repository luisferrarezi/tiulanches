package br.com.fiap.tiulanches.core.domain.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ITENS_PEDIDOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Código do item após ser criado", example = "1", required = true)	
	private long idItem;	
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name="id_pedido", nullable = false)
	@Schema(description = "Pedido que o item pertence", required = true)	
	private Pedido pedido;	
	
	@ManyToOne
	@JoinColumn(name="id_produto")			
	@Schema(description = "Produto informado", required = true)	
	private Produto produto;
	
	@NotNull
	@Positive
	@Schema(description = "Preço do produto", example = "19.00", required = true)	
	private BigDecimal precoUnitario;
	
	@Min(value = 1, message="Quantidade mínima é 1")	
	@Schema(description = "Quantidade total do produto", example = "3", required = true)	
	private int quantidade;
	
	@Size(max=100)
	@Schema(description = "Observações do cliente para esse produto", example = "Sem queijo e mostarda", maxLength = 100)	
	private String observacao;
}
