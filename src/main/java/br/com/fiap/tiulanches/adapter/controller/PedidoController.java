package br.com.fiap.tiulanches.adapter.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.core.enums.StatusPedido;
import java.util.List;

public interface PedidoController {	
	public Page<PedidoDto> consultaPaginada(Pageable paginacao);
	public List<PedidoDto> consultaByStatusPago(StatusPedido status, Pago pago);	
	public PedidoDto detalhar(Long id);	
	public PedidoDto cadastrar(PedidoDto dto);	
	public void cancelar(Long id);
	public void preparar(Long id);	
	public void entregar(Long id);
	public void finalizar(Long id);
}
