package br.com.fiap.tiulanches.adapter.controller;

import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;

public interface PagamentoController {
	public PagamentoDto consultaPagamento(Long idPedido);
}
