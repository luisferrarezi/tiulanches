package br.com.fiap.tiulanches.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.tiulanches.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.core.entitie.cliente.Cliente;
import br.com.fiap.tiulanches.core.entitie.pedido.ItemPedido;
import br.com.fiap.tiulanches.core.entitie.pedido.Pedido;
import br.com.fiap.tiulanches.core.entitie.produto.Produto;
import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.core.enums.StatusPedido;
import br.com.fiap.tiulanches.core.usecase.PedidoUseCase;
import br.com.fiap.tiulanches.repository.cliente.ClienteRepository;
import br.com.fiap.tiulanches.repository.pedido.PedidoRepository;
import br.com.fiap.tiulanches.repository.produto.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService implements PedidoUseCase {
	private final PedidoRepository pedidoRepository;
	private final ProdutoRepository produtoRepository;
	private final ClienteRepository clienteRepository;
	
	@Autowired
	public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
	}
	
	public Page<PedidoDto> consultaPaginada(Pageable paginacao){
		return pedidoRepository.findAll(paginacao).map(PedidoDto::new);
	}

	public List<PedidoDto> consultaByStatusPago(StatusPedido status, Pago pago){
		List<Pedido> listPedido = pedidoRepository.findByStatusPago(status, pago);
		
		return listPedido.stream().map(pedido -> new PedidoDto(pedido)).collect(Collectors.toList());
	}	
	
	public PedidoDto consultaById(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return new PedidoDto(pedido);
    }
	
	public PedidoDto cadastrar(PedidoDto dto){
		Pedido pedido = new Pedido();
		pedido.cadastrar(dto);
		
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
	
	public void cancelar(Long id){
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		pedido.cancelar();
		
		pedidoRepository.save(pedido);
	}	
}