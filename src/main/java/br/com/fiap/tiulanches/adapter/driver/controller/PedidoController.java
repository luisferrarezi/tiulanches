package br.com.fiap.tiulanches.adapter.driver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
	
	private static Logger logger = LoggerFactory.getLogger(PedidoController.class);
	
	@GetMapping	
	public ResponseEntity<Page<PedidoDto>> consultar(@PageableDefault(size=10) Pageable paginacao){
		logger.info("Consultar pedidos");
		
		Page<PedidoDto> page = service.consultaPedidos(paginacao); 
		
		return ResponseEntity.ok(page);
	}	
		
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> detalhar(@PathVariable @NotNull Long id){
		logger.info("Consultar pedido pelo idPedido: " + id.toString());
		
		PedidoDto pedido = service.consultaPedidosById(id);
		
		return ResponseEntity.ok(pedido);
	}
	
	@PostMapping
	public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid PedidoDto dto, UriComponentsBuilder uriBuilder){
		logger.info("Incluir pedido");
		
		PedidoDto pedido = service.criarPedido(dto);
		URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.idPedido()).toUri();
		
		return ResponseEntity.created(endereco).body(pedido);
	}
	
	@PutMapping("/cancelamento/{id}")
	public ResponseEntity<PedidoDto> cancelar(@PathVariable @NotNull Long id){
		logger.info("Cancelar pedido pelo idPedido: " + id.toString());
		
		service.cancelarPedido(id);		
		
		return ResponseEntity.ok().build();
	}
}
