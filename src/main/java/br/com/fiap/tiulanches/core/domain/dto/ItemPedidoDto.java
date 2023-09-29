package br.com.fiap.tiulanches.core.domain.dto;

import java.math.BigDecimal;

import br.com.fiap.tiulanches.core.domain.entities.ItemPedido;
import br.com.fiap.tiulanches.core.domain.entities.Pedido;
import br.com.fiap.tiulanches.core.domain.entities.Produto;

public record ItemPedidoDto(long idItem,
							Pedido pedido,
							Produto produto,
							BigDecimal precoUnitario,
							int quantidade,
							String observacao) {
	public ItemPedidoDto(ItemPedido itemPedido) {
		this(itemPedido.getIdItem(), itemPedido.getPedido(), itemPedido.getProduto(), itemPedido.getPrecoUnitario(), itemPedido.getQuantidade(), itemPedido.getObservacao());
	}
}
