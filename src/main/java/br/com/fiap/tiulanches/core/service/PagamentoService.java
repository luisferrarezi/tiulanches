package br.com.fiap.tiulanches.core.service;

import org.springframework.stereotype.Service;
import br.com.fiap.tiulanches.adapter.controller.PagamentoController;
import br.com.fiap.tiulanches.adapter.repository.pagamento.ConsultaPagamentoDto;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PagamentoService implements PagamentoController {

	private final PagamentoRepository repository;
	
	public PagamentoService(PagamentoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public ConsultaPagamentoDto consultaPagamento(Long idPedido) {		
		try {			
			return new ConsultaPagamentoDto(repository.consultaPagamento(idPedido));
		}
		catch(Exception e) {
			throw new EntityNotFoundException();		
			
		}
	}

}
