package br.com.fiap.tiulanches.dtos;

import java.math.BigDecimal;

import br.com.fiap.tiulanches.entities.ItemPedido;
import br.com.fiap.tiulanches.entities.Pedido;
import br.com.fiap.tiulanches.entities.Produto;
import io.swagger.v3.oas.annotations.media.Schema;

public record ItemPedidoDto(@Schema(description = "Código do item após ser criado", example = "17", required = true)
		                    long idItem,
		                    @Schema(description = "Pedido que o item pertence", required = true)
							Pedido pedido,
							@Schema(description = "Produto informado", required = true)
							Produto produto,
							@Schema(description = "Preço do produto", example = "19.00", required = true)
							BigDecimal precoUnitario,
							@Schema(description = "Quantidade total do produto", example = "3", required = true)
							int quantidade,
							@Schema(description = "Observações do cliente para esse produto", example = "Sem queijo e mostarda", maxLength = 100)
							String observacao) {
	public ItemPedidoDto(ItemPedido itemPedido) {
		this(itemPedido.getIdItem(), itemPedido.getPedido(), itemPedido.getProduto(), itemPedido.getPrecoUnitario(), itemPedido.getQuantidade(), itemPedido.getObservacao());
	}
}
