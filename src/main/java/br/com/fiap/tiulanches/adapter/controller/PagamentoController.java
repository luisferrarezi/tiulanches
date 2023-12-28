package br.com.fiap.tiulanches.adapter.controller;

import br.com.fiap.tiulanches.adapter.repository.pagamento.ConsultaPagamentoDto;
import br.com.fiap.tiulanches.core.enums.Pago;

public interface PagamentoController {
	public ConsultaPagamentoDto consultaPagamento(Long idPedido);	
	public void registra(long idPagamento, Pago pago, String idMercadoPago, String ticketUrl);
}
