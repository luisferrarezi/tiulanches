package br.com.fiap.tiulanches.core.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.fiap.tiulanches.adapter.controller.PagamentoController;
import br.com.fiap.tiulanches.adapter.repository.pagamento.ConsultaPagamentoDto;
import br.com.fiap.tiulanches.adapter.repository.pagamento.ConsultaPagamentoRepository;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoRepository;
import br.com.fiap.tiulanches.core.entity.pagamento.Pagamento;
import br.com.fiap.tiulanches.core.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PagamentoService implements PagamentoController {

	private final PagamentoRepository pagamentoRepository;
	private final ConsultaPagamentoRepository consultaPagamentoRepository;
	
	public PagamentoService(ConsultaPagamentoRepository consultaPagamentoRepository, PagamentoRepository pagamentoRepository) {
		this.consultaPagamentoRepository = consultaPagamentoRepository;
		this.pagamentoRepository = pagamentoRepository;
	}
	
	@Override
	public ConsultaPagamentoDto consultaPagamento(Long idPedido) {		
		try {			
			return new ConsultaPagamentoDto(consultaPagamentoRepository.consultaPagamento(idPedido));
		}
		catch(Exception e) {
			throw new EntityNotFoundException();
		}
	}	

	@Override
	public void registra(PagamentoDto dto) {
		Pagamento pagamento = pagamentoRepository.findById(dto.idPagamento()).orElseThrow(() -> new EntityNotFoundException());
				
		try {			
			pagamento.registrar(dto);
			pagamentoRepository.save(pagamento);			
		} catch (Exception e) {
			throw new BusinessException("Falha ao alterar pagamento!", HttpStatus.BAD_REQUEST, e.getMessage());
		}			
	}
}
