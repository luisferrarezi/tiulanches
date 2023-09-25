package br.com.fiap.tiulanches.core.domain.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
	private long idItem;	
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name="id_pedido", nullable = false)
	private Pedido pedido;	
	
	@ManyToOne
	@JoinColumn(name="id_produto")			
	private Produto produto;
	
	@NotNull
	@Positive
	private BigDecimal precoUnitario;
	
	@NotNull
	private int quantidade;
	
	@Size(max=100)
	private String observacao;
}
