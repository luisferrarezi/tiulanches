package br.com.fiap.tiulanches.adapter.repository.pagamento;

import br.com.fiap.tiulanches.core.entitie.pagamento.Pagamento;
import br.com.fiap.tiulanches.core.enums.Pago;
import io.swagger.v3.oas.annotations.media.Schema;

public record PagamentoDto(@Schema(description = "Código de pagamento do pedido interno", example = "7", required = true)
					       long idPagamento,					       
					       @Schema(description = "Código do pedido após ser criado", example = "17", required = true)
						   Pago pago,
						   @Schema(description = "Codigo do pedido no mercado pago", example = "1320363299")
						   String idMercadoPago,
						   @Schema(description = "URL para cliente realizar pagamento", example = "https://www.mercadopago.com.br/payments/123456789/ticket?caller_id=123456&hash=123e4567-e89b-12d3-a456-426655440000")
						   String ticketUrl) {
	
	public PagamentoDto(Pagamento pagamento) {
		this(pagamento.getIdPagamento(), pagamento.getPago(), pagamento.getIdMercadoPago(), pagamento.getTicketUrl());
	}
}
