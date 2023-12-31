package br.com.fiap.tiulanches.adapter.controller;

import br.com.fiap.tiulanches.adapter.repository.pagamento.ConsultaPagamentoDto;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;

public interface PagamentoController {
	public ConsultaPagamentoDto consultaPagamento(Long idPedido);	
	public void registra(PagamentoDto dto);
}
