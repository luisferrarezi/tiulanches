package br.com.fiap.tiulanches.adapter.repository.pagamento;

import br.com.fiap.tiulanches.core.entitie.pagamento.Pagamento;
import br.com.fiap.tiulanches.core.enums.Pago;
import io.swagger.v3.oas.annotations.media.Schema;

public record PagamentoDto(@Schema(description = "Código do pedido após ser criado", example = "17", required = true)
					       long idPedido,
					       @Schema(implementation = Pago.class, description = "Pedido pago", example = "SIM", required = true)
						   Pago pago) {
	public PagamentoDto(Pagamento pagamento) {
		 this(pagamento.getIdPedido(), pagamento.getPago());
	}	
}
