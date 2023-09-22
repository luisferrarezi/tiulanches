package br.com.fiap.tiulanches.core.domain.dto;

import br.com.fiap.tiulanches.core.domain.entities.Pedido;
import br.com.fiap.tiulanches.core.domain.entities.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDto {
	private long idItem;
	private Pedido pedido;
	private Produto produto;
	private int quantidade;
	private String observacao;
}
