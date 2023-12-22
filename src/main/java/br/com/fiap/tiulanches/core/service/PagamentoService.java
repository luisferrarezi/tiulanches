package br.com.fiap.tiulanches.core.service;

import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.controller.PagamentoController;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoRepository;
import br.com.fiap.tiulanches.core.entitie.pagamento.Pagamento;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PagamentoService implements PagamentoController {

	private final PagamentoRepository repository;
	
	public PagamentoService(PagamentoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public PagamentoDto consultaPagamento(Long idPedido) {
		
		try {
			Pagamento pagamento = repository.consultaPagamento(idPedido);
			return new PagamentoDto(pagamento);
		}
		catch(Exception e) {
			throw new EntityNotFoundException();		
			
		}
	}

}
