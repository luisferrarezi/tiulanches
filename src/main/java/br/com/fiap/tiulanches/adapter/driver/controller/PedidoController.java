package br.com.fiap.tiulanches.adapter.driver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.tiulanches.core.domain.service.PedidoService;
import br.com.fiap.tiulanches.adapter.infra.swagger.PedidoResponseSwagger;
import br.com.fiap.tiulanches.core.domain.dto.PedidoDto;
import br.com.fiap.tiulanches.core.domain.enums.Pago;
import br.com.fiap.tiulanches.core.domain.enums.StatusPedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	private static Logger logger = LoggerFactory.getLogger(PedidoController.class);
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Lista todos os pedidos cadastrados", 
	   		   description = "O retorno é paginado e o padrão são 10 registros por página", 
	   		   tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", 
					     description = "Sucesso, lista todos os produtos em paginação",
					     content=@Content(schema = @Schema(example = PedidoResponseSwagger.GETBYPAGE)))
	})		
	public ResponseEntity<Page<PedidoDto>> consultar(@ParameterObject @PageableDefault(size=10) Pageable paginacao){
		logger.info("Consultar pedidos");
		
		Page<PedidoDto> page = service.consultaPedidos(paginacao); 
		
		return ResponseEntity.ok(page);
	}	

	@GetMapping(value = "/status/{status}/pago/{pago}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Lista todos os pedidos cadastrados por status e se está pago", 
	           description = "Retorna uma lista não paginada dos pedidos por status e se está pago", 
	           tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, lista todos os pedidos por status e se está pago sem paginação")
	})			
	public List<PedidoDto> consultarByStatusPago(@ParameterObject 
			                                     @PathVariable 
			                                     @NotNull
			                                     @Schema(implementation = StatusPedido.class, description = "Status Pedido", example = "RECEBIDO", required = true)
			                                     StatusPedido status,
			                                     @PathVariable
			                                     @NotNull
			                                     @Schema(implementation = Pago.class, description = "Pedido pago", example = "SIM", required = true)
			                                     Pago pago){
		logger.info("Consultar pedidos pelo status: " + status.toString() + " e pago: " + pago.toString());
		
		return service.consultaPedidosByStatusPago(status, pago);
	}	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Retorna dados do pedido", 
	   		   description = "Retorna todos os dados do pedido do código informado", 
	   		   tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", 
					     description = "Sucesso, retorna dados do pedido",
					     content=@Content(schema = @Schema(example = PedidoResponseSwagger.GETBYID))),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})		
	public ResponseEntity<PedidoDto> detalhar(@ParameterObject 
			                                  @PathVariable 
			                                  @NotNull
			                                  @Schema(description = "Código do pedido após ser criado", example = "1", required = true)                     
			                                  Long id){
		logger.info("Consultar pedido pelo idPedido: " + id.toString());
		
		PedidoDto pedido = service.consultaPedidosById(id);
		
		return ResponseEntity.ok(pedido);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Cadastra o pedido", 
	   		   description = "Cria o pedido e retorna o registro cadastrado", 
	   		   tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", 
                         description = "Sucesso, produto cadastrado",
                         content=@Content(schema = @Schema(example = PedidoResponseSwagger.CREATED))),
			@ApiResponse(responseCode = "400", 
			             description = "Falha, não cadastra o produto por faltar informação ou com informação errada",
			             content=@Content(schema = @Schema(example = PedidoResponseSwagger.BADREQUEST))),
			@ApiResponse(responseCode = "404", description = "Falha, produto ou cliente não encontrado", content=@Content(schema = @Schema(hidden = true)))			
	})					
	public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid @Schema(example = PedidoResponseSwagger.POST) PedidoDto dto, UriComponentsBuilder uriBuilder){
		logger.info("Incluir pedido");
		
		PedidoDto pedido = service.criarPedido(dto);
		URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.idPedido()).toUri();
		
		return ResponseEntity.created(endereco).body(pedido);
	}
	
	@PutMapping(value = "/cancelamento/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Cancela o pedido", 
	   		   description = "Retorna todos os dados do pedido do código informado", 
	   		   tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, pedido cancelado", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public ResponseEntity<PedidoDto> cancelar(@ParameterObject 
			                                  @PathVariable 
			                                  @NotNull
			                                  @Schema(description = "Código do pedido após ser criado", example = "1", required = true)
			                                  Long id){
		logger.info("Cancelar pedido pelo idPedido: " + id.toString());
		
		service.cancelarPedido(id);		
		
		return ResponseEntity.ok().build();
	}
}
