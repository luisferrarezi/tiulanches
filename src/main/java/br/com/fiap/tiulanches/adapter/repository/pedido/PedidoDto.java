package br.com.fiap.tiulanches.adapter.repository.pedido;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.core.entitie.pedido.Pedido;
import br.com.fiap.tiulanches.core.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;

public record PedidoDto (@Schema(description = "Código do pedido após ser criado", example = "17", required = true)
						 long idPedido,
						 @Schema(description = "Cliente caso ele queira se identificar")
						 ClienteDto cliente,
						 @Schema(implementation = StatusPedido.class, description = "Status do pedido", example = "RECEBIDO", required = true)						 
						 StatusPedido status,
						 @Schema(description = "Pagamento do pedido")
						 PagamentoDto pagamento,
						 @Schema(description = "Lista itens do pedido", required = true)						 
						 List<ItemPedidoDto> listItemPedido){	
	public PedidoDto(Pedido pedido) {
		this(pedido.getIdPedido(), 
			 pedido.getCliente()!= null ? new ClienteDto(pedido.getCliente()) : null, 
			 pedido.getStatus(), 
			 new PagamentoDto(pedido.getPagamento()), 
			 pedido.getListItemPedido().stream().map(itemPedido -> new ItemPedidoDto(itemPedido)).collect(Collectors.toList()));		
	}	
}
