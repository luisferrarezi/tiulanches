package br.com.fiap.tiulanches.adapter.driven.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.core.domain.dto.PedidoDto;
import br.com.fiap.tiulanches.core.domain.entities.ItemPedido;
import br.com.fiap.tiulanches.core.domain.entities.Pedido;
import br.com.fiap.tiulanches.core.domain.entities.Produto;
import br.com.fiap.tiulanches.core.domain.enums.Pago;
import br.com.fiap.tiulanches.core.domain.enums.StatusPedido;
import br.com.fiap.tiulanches.core.domain.repository.PedidoRepository;
import br.com.fiap.tiulanches.core.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<PedidoDto> consultaPedidos(Pageable paginacao){
		return pedidoRepository.findAll(paginacao).map(p -> modelMapper.map(p, PedidoDto.class));
	}
	
	public PedidoDto consultaPedidosById(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pedido, PedidoDto.class);
    }
	
	public PedidoDto criarPedido(PedidoDto dto){
		Pedido pedido = modelMapper.map(dto, Pedido.class);
		pedido.setPago(Pago.SIM);
		pedido.setStatus(StatusPedido.RECEBIDO);
		pedido.setQrcode("qrcode123456");
		
        List<ItemPedido> listItemPedido = pedido.getListItemPedido();
		
		for(ItemPedido itemPedido : listItemPedido) {
			itemPedido.setPedido(pedido);
			
			Produto produto = produtoRepository.findById(itemPedido.getProduto().getIdProduto()).orElseThrow(() -> new EntityNotFoundException());
			itemPedido.setProduto(produto);
			itemPedido.setPrecoUnitario(produto.getPreco());
		}
		
		pedidoRepository.save(pedido);		
		
		return modelMapper.map(pedido, PedidoDto.class);
	}
	
	public PedidoDto alterarPedido(Long id, PedidoDto dto){
		Pedido pedido = modelMapper.map(dto, Pedido.class);
		pedido.setIdPedido(id);
		pedido.setPago(Pago.SIM);
		pedido.setStatus(StatusPedido.RECEBIDO);
		pedido.setQrcode("qrcode123456");
		
        List<ItemPedido> listItemPedido = pedido.getListItemPedido();		
		for(ItemPedido itemPedido : listItemPedido) {
			itemPedido.setPedido(pedido);
			
			Produto produto = produtoRepository.findById(itemPedido.getProduto().getIdProduto()).orElseThrow(() -> new EntityNotFoundException());
			itemPedido.setProduto(produto);
			itemPedido.setPrecoUnitario(produto.getPreco());			
		}		
		
		pedido = pedidoRepository.save(pedido);			
				
		return modelMapper.map(pedido, PedidoDto.class);
	}	
	
	public void excluirPedido(Long id){
		pedidoRepository.deleteById(id);
	}	
}
