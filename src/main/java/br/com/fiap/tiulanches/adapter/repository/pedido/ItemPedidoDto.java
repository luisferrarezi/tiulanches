package br.com.fiap.tiulanches.adapter.repository.pedido;

import java.math.BigDecimal;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.entity.pedido.ItemPedido;
import io.swagger.v3.oas.annotations.media.Schema;

public record ItemPedidoDto(@Schema(description = "Código do item após ser criado", example = "17", required = true)
		                    long idItem,
		                    @Schema(description = "Pedido que o item pertence", required = true)
							long idPedido,
							@Schema(description = "Produto informado", required = true)
							ProdutoDto produto,
							@Schema(description = "Preço do produto", example = "19.00", required = true)
							BigDecimal precoUnitario,
							@Schema(description = "Quantidade total do produto", example = "3", required = true)
							int quantidade,
							@Schema(description = "Observações do cliente para esse produto", example = "Sem queijo e mostarda", maxLength = 100)
							String observacao) {
	public ItemPedidoDto(ItemPedido itemPedido) {
		this(itemPedido.getIdItem(), 
			 itemPedido.getIdPedido(), 
			 new ProdutoDto(itemPedido.getProduto()), 
			 itemPedido.getPrecoUnitario(), 
			 itemPedido.getQuantidade(), 
			 itemPedido.getObservacao());
	}
}
