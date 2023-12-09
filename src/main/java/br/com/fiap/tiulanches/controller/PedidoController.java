package br.com.fiap.tiulanches.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import br.com.fiap.tiulanches.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.adapter.PedidoGateway;
import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.core.enums.StatusPedido;
import br.com.fiap.tiulanches.core.usecase.PedidoUseCase;

import java.util.List;

@Controller
public class PedidoController implements PedidoGateway {

	private final PedidoUseCase useCase; 
	
	@Autowired
	public PedidoController(PedidoUseCase useCase) {
		this.useCase = useCase;
	};
	
	public Page<PedidoDto> consultaPaginada(Pageable paginacao){	
		Page<PedidoDto> page = useCase.consultaPaginada(paginacao); 
		
		return page;
	}	

	public List<PedidoDto> consultaByStatusPago(StatusPedido status, Pago pago){
		return useCase.consultaByStatusPago(status, pago);
	}	
	
	public PedidoDto detalhar(Long id){
		return useCase.consultaById(id);
	}
	
	public PedidoDto cadastrar(PedidoDto dto){
		return useCase.cadastrar(dto);
	}
	
	public void cancelar(Long id){		
		useCase.cancelar(id);
	}
}
