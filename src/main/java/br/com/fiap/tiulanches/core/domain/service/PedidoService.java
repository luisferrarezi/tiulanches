package br.com.fiap.tiulanches.core.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.tiulanches.core.domain.dto.PedidoDto;
import br.com.fiap.tiulanches.core.domain.entities.Cliente;
import br.com.fiap.tiulanches.core.domain.entities.ItemPedido;
import br.com.fiap.tiulanches.core.domain.entities.Pedido;
import br.com.fiap.tiulanches.core.domain.entities.Produto;
import br.com.fiap.tiulanches.adapter.driven.repository.ClienteRepository;
import br.com.fiap.tiulanches.adapter.driven.repository.PedidoRepository;
import br.com.fiap.tiulanches.adapter.driven.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Page<PedidoDto> consultaPedidos(Pageable paginacao){
		return pedidoRepository.findAll(paginacao).map(PedidoDto::new);
	}
	
	public PedidoDto consultaPedidosById(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return new PedidoDto(pedido);
    }
	
	public PedidoDto criarPedido(PedidoDto dto){
		Pedido pedido = new Pedido();
		pedido.criar(dto);
		
		if (dto.cliente() != null) {
			Cliente cliente = clienteRepository.findById(dto.cliente().getCpf()).orElseThrow(() -> new EntityNotFoundException());		
			pedido.setCliente(cliente);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		List<ItemPedido> listItemPedido = mapper.convertValue(dto.listItemPedido(), new TypeReference<List<ItemPedido>>() {});
		
		for (ItemPedido itemPedido : listItemPedido) {
			Produto produto = produtoRepository.findById(itemPedido.getProduto().getIdProduto()).orElseThrow(() -> new EntityNotFoundException());
			itemPedido.setProduto(produto);
			itemPedido.setPrecoUnitario(produto.getPreco());
			
			pedido.adicionarItem(itemPedido);
		}		
		
		pedidoRepository.save(pedido);		
		
		return new PedidoDto(pedido);
	}
	
	public void cancelarPedido(Long id){
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		pedido.cancelar();
		
		pedidoRepository.save(pedido);
	}	
}