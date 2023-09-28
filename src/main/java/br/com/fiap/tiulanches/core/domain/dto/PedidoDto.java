package br.com.fiap.tiulanches.core.domain.dto;


import java.util.List;

import br.com.fiap.tiulanches.core.domain.entities.Cliente;
import br.com.fiap.tiulanches.core.domain.entities.ItemPedido;
import br.com.fiap.tiulanches.core.domain.entities.Pedido;
import br.com.fiap.tiulanches.core.domain.enums.Pago;
import br.com.fiap.tiulanches.core.domain.enums.StatusPedido;

public record PedidoDto (long idPedido,
						 Cliente cliente,
						 StatusPedido status,
						 String qrcode,
						 Pago pago,
						 List<ItemPedido> listItemPedido){	
	public PedidoDto(Pedido pedido) {
		this(pedido.getIdPedido(), pedido.getCliente(), pedido.getStatus(), pedido.getQrcode(), pedido.getPago(), pedido.getListItemPedido());
	}	
}
