package br.com.fiap.tiulanches.adapter.repository.pedido;

import java.util.List;

import br.com.fiap.tiulanches.core.entitie.cliente.Cliente;
import br.com.fiap.tiulanches.core.entitie.pagamento.Pagamento;
import br.com.fiap.tiulanches.core.entitie.pedido.ItemPedido;
import br.com.fiap.tiulanches.core.entitie.pedido.Pedido;
import br.com.fiap.tiulanches.core.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;

public record PedidoDto (@Schema(description = "Código do pedido após ser criado", example = "17", required = true)
						 long idPedido,
						 @Schema(description = "Cliente caso ele queira se identificar")
						 Cliente cliente,
						 @Schema(implementation = StatusPedido.class, description = "Status do pedido", example = "RECEBIDO", required = true)						 
						 StatusPedido status,
						 @Schema(description = "Pagamento do pedido")
						 Pagamento pagamento,
						 @Schema(description = "Lista itens do pedido", required = true)						 
						 List<ItemPedido> listItemPedido){	
	public PedidoDto(Pedido pedido) {
		this(pedido.getIdPedido(), pedido.getCliente(), pedido.getStatus(), pedido.getPagamento(), pedido.getListItemPedido());
	}	
}
