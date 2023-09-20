package br.com.fiap.tiulanches.adapter.driver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.tiulanches.adapter.driven.service.PedidoService;
import br.com.fiap.tiulanches.core.domain.dto.PedidoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping	
	public Page<PedidoDto> consultar(@PageableDefault(size=10) Pageable paginacao){
		return service.consultaPedidos(paginacao);
	}	
		
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> detalhar(@PathVariable @NotNull Long id){
		PedidoDto pedido = service.consultaPedidosById(id);
		
		return ResponseEntity.ok(pedido);
	}
	
	@PostMapping
	public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid PedidoDto dto, UriComponentsBuilder uriBuilder){
		PedidoDto pedido = service.criarPedido(dto);
		URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getIdPedido()).toUri();
		
		return ResponseEntity.created(endereco).body(pedido);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDto> alterar(@PathVariable @NotNull Long id, @RequestBody @Valid PedidoDto dto){
		PedidoDto pedidoAlterado = service.alterarPedido(id, dto);		
		
		return ResponseEntity.ok(pedidoAlterado);
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PedidoDto> excluir(@PathVariable @NotNull Long id){
		service.excluirPedido(id);		
		
		return ResponseEntity.noContent().build();
	}		
}
