package br.com.fiap.tiulanches.adapter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.core.enums.StatusPedido;
import br.com.fiap.tiulanches.repository.pedido.PedidoDto;

public interface PedidoGateway {
	public Page<PedidoDto> consultaPaginada(Pageable paginacao);
	public List<PedidoDto> consultaByStatusPago(StatusPedido status, Pago pago);
	public PedidoDto detalhar(Long id);	
	public PedidoDto cadastrar(PedidoDto dto);	
	public void cancelar(Long id);
}
