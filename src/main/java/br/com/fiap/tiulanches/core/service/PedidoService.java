package br.com.fiap.tiulanches.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.adapter.controller.PedidoController;
import br.com.fiap.tiulanches.core.entity.cliente.Cliente;
import br.com.fiap.tiulanches.core.entity.pedido.ItemPedido;
import br.com.fiap.tiulanches.core.entity.pedido.Pedido;
import br.com.fiap.tiulanches.core.entity.produto.Produto;
import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.core.enums.StatusPedido;
import br.com.fiap.tiulanches.core.exception.BusinessException;
import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteRepository;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoRepository;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService implements PedidoController {
	private final PedidoRepository pedidoRepository;
	private final ProdutoRepository produtoRepository;
	private final ClienteRepository clienteRepository;
	
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
	
	public PedidoDto detalhar(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return new PedidoDto(pedido);
    }
	
	public PedidoDto cadastrar(PedidoDto dto){
		Pedido pedido = new Pedido();
		pedido.cadastrar(dto);
		
		if (dto.cliente() != null) {
			Cliente cliente = clienteRepository.findById(dto.cliente().cpf()).orElseThrow(() -> new EntityNotFoundException());

			if (!cliente.isLogado()){
				throw new BusinessException("Cliente não logado na aplicação!", HttpStatus.UNAUTHORIZED, new String("Cliente"));
			}

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
		
		if (pedido.isPermiteCancelar()) {
			pedido.cancelar();
			pedidoRepository.save(pedido);
		} else {
			throw new BusinessException("Pedido não pode ser cancelado!", HttpStatus.BAD_REQUEST, new String("Pedido"));
		}
	}
	
	public void preparar(Long id){
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		if (pedido.isPermitePreparo()) {		
			pedido.preparar();		
			pedidoRepository.save(pedido);
		} else {
			throw new BusinessException("Pedido não pode ser preparado!", HttpStatus.BAD_REQUEST, new String("Pedido"));
		}
	}
	
	@Override
	public void entregar(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		if (pedido.isPermiteEntregar()) {		
			pedido.entregar();		
			pedidoRepository.save(pedido);
		} else {
			throw new BusinessException("Pedido não pode ser entregue!", HttpStatus.BAD_REQUEST, new String("Pedido"));
		}
	}

	@Override
	public void finalizar(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		if (pedido.isPermiteFinalizar()) {		
			pedido.finalizar();		
			pedidoRepository.save(pedido);
		} else {
			throw new BusinessException("Pedido não pode ser finalizado!", HttpStatus.BAD_REQUEST, new String("Pedido"));
		}
	}	
}