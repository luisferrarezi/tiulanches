package br.com.fiap.tiulanches.adapter.driven.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.core.domain.dto.PedidoDto;
import br.com.fiap.tiulanches.core.domain.entities.Pedido;
import br.com.fiap.tiulanches.core.domain.enums.Pago;
import br.com.fiap.tiulanches.core.domain.enums.StatusPedido;
import br.com.fiap.tiulanches.core.domain.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<PedidoDto> consultaPedidos(Pageable paginacao){
		return repository.findAll(paginacao).map(p -> modelMapper.map(p, PedidoDto.class));
	}
	
	public PedidoDto consultaPedidosById(Long id) {
		Pedido pedido = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pedido, PedidoDto.class);
    }
	
	public PedidoDto criarPedido(PedidoDto dto){
		Pedido pedido = modelMapper.map(dto, Pedido.class);
		pedido.setPago(Pago.SIM);
		pedido.setStatus(StatusPedido.RECEBIDO);
		pedido.setQrcode("qrcode123456");
		repository.save(pedido);
		
		return modelMapper.map(pedido, PedidoDto.class);
	}
	
	public PedidoDto alterarPedido(Long id, PedidoDto dto){
		Pedido pedido = modelMapper.map(dto, Pedido.class);
		pedido.setIdPedido(id);
		pedido.setPago(Pago.SIM);
		pedido.setStatus(StatusPedido.RECEBIDO);
		pedido.setQrcode("qrcode123456");
		
		pedido = repository.save(pedido);
		
		return modelMapper.map(pedido, PedidoDto.class);
	}	
	
	public void excluirPedido(Long id){
		repository.deleteById(id);
	}	
}
