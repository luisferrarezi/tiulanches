package br.com.fiap.tiulanches.core.domain.dto;


import java.util.List;

import br.com.fiap.tiulanches.core.domain.entities.Cliente;
import br.com.fiap.tiulanches.core.domain.entities.ItemPedido;
import br.com.fiap.tiulanches.core.domain.entities.Pedido;
import br.com.fiap.tiulanches.core.domain.enums.Pago;
import br.com.fiap.tiulanches.core.domain.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

public record PedidoDto (@Schema(description = "Código do pedido após ser criado", example = "1", required = true)
						 long idPedido,
						 @Schema(description = "Cliente caso ele queira se identificar")
						 Cliente cliente,
						 @Schema(implementation = StatusPedido.class, description = "Status do pedido", example = "RECEBIDO", required = true)						 
						 StatusPedido status,
						 @Schema(description = "QrCode", example = "gfdsgsdfgregedsrfg4353426tyrgfeg45", required = true, maxLength = 400)
						 String qrcode,
						 @Schema(implementation = Pago.class, description = "Pedido pago", example = "SIM", required = true)
						 Pago pago,
						 @Schema(description = "Lista itens do pedido", required = true)
						 
						 List<ItemPedido> listItemPedido){	
	public PedidoDto(Pedido pedido) {
		this(pedido.getIdPedido(), pedido.getCliente(), pedido.getStatus(), pedido.getQrcode(), pedido.getPago(), pedido.getListItemPedido());
	}	
}
