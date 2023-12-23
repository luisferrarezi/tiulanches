package br.com.fiap.tiulanches.adapter.controller;

import br.com.fiap.tiulanches.adapter.repository.pagamento.ConsultaPagamentoDto;

public interface PagamentoController {
	public ConsultaPagamentoDto consultaPagamento(Long idPedido);
}
