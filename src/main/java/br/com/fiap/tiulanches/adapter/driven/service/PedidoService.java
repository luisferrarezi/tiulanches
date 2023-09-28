package br.com.fiap.tiulanches.adapter.driven.service;

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
import br.com.fiap.tiulanches.core.domain.repository.ClienteRepository;
import br.com.fiap.tiulanches.core.domain.repository.PedidoRepository;
import br.com.fiap.tiulanches.core.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

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
	
	@Transactional
	public PedidoDto alterarPedido(Long id, PedidoDto dto){
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		if (dto.cliente() != null) {
			Cliente cliente = clienteRepository.findById(dto.cliente().getCpf()).orElseThrow(() -> new EntityNotFoundException());		
			pedido.setCliente(cliente);
		}
		
		pedido.atualizar(dto);
		
        List<ItemPedido> listItemPedido = pedido.getListItemPedido();		
		for(ItemPedido itemPedido : listItemPedido) {
			itemPedido.setPedido(pedido);
			
			Produto produto = produtoRepository.findById(itemPedido.getProduto().getIdProduto()).orElseThrow(() -> new EntityNotFoundException());
			itemPedido.setProduto(produto);
			itemPedido.setPrecoUnitario(produto.getPreco());			
		}			
				
		return new PedidoDto(pedido);
	}	
	
	public void excluirPedido(Long id){
		pedidoRepository.deleteById(id);
	}	
}
