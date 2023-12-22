package br.com.fiap.tiulanches.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.controller.PainelPedidoController;
import br.com.fiap.tiulanches.adapter.repository.painelpedido.PainelPedidoDto;
import br.com.fiap.tiulanches.adapter.repository.painelpedido.PainelPedidoRepository;
import br.com.fiap.tiulanches.core.entitie.painelpedido.PainelPedido;
import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.core.enums.StatusPedido;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PainelPedidoService implements PainelPedidoController{
	
	private final PainelPedidoRepository repository;
	
	public PainelPedidoService(PainelPedidoRepository repository) {
		this.repository = repository;		
	}	
	
	public List<PainelPedidoDto> consultaPainelPedido() {
		try {
			List<PainelPedido> listPainelPedido = repository.consultaPainelPedido(StatusPedido.RECEBIDO.ordinal(), 
				 														      StatusPedido.PREPARACAO.ordinal(), 
				 															  StatusPedido.PRONTO.ordinal(), 
				 															  Pago.SIM.ordinal());
		
			return listPainelPedido.stream().map(painelPedido -> new PainelPedidoDto(painelPedido)).collect(Collectors.toList());
		}
		catch(Exception e) {
			throw new EntityNotFoundException();		
			
		} 
	}

}
