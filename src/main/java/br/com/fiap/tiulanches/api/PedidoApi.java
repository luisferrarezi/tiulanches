package br.com.fiap.tiulanches.api;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
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

import br.com.fiap.tiulanches.adapter.controller.PreferenciaExternoController;
import br.com.fiap.tiulanches.adapter.controller.PedidoController;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.core.enums.StatusPedido;
import br.com.fiap.tiulanches.infra.swagger.PedidoResponseSwagger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoApi {
	
	private final PedidoController pedidoController;
	private final PreferenciaExternoController preferenciaExternoController;
	
	public PedidoApi(PedidoController pedidoController, PreferenciaExternoController preferenciaExternoController){
		this.pedidoController = pedidoController;
		this.preferenciaExternoController = preferenciaExternoController;
	};
	
	private static Logger logger = LoggerFactory.getLogger(PedidoApi.class);
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Lista todos os pedidos", description = "Retorno paginado, padrão de 10 registros por página", tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, lista todos os produtos em paginação", content=@Content(schema = @Schema(example = PedidoResponseSwagger.GETBYPAGE)))
	})		
	public ResponseEntity<Page<PedidoDto>> consultar(@ParameterObject @PageableDefault(size=10) Pageable paginacao){
		logger.info("Consultar pedidos");
		
		Page<PedidoDto> page = pedidoController.consultaPaginada(paginacao); 
		
		return ResponseEntity.ok(page);
	}	

	@GetMapping(value = "/status/{status}/pago/{pago}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Lista todos os pedidos por status e status de pagamento", description = "Lista não paginada dos pedidos por status e status de pagamento", tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, lista todos os pedidos por status e status de pagamento sem paginação")
	})			
	public List<PedidoDto> consultarByStatusPago(@ParameterObject @PathVariable @NotNull
			                                     @Schema(implementation = StatusPedido.class, description = "Status Pedido", example = "RECEBIDO", required = true)
			                                     StatusPedido status,
			                                     @PathVariable @NotNull
			                                     @Schema(implementation = Pago.class, description = "Pedido pago", example = "SIM", required = true)
			                                     Pago pago){
		logger.info("Consultar pedidos pelo status: " + status.toString() + " e pago: " + pago.toString());
		
		return pedidoController.consultaByStatusPago(status, pago);
	}	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Retorna dados do pedido", description = "Retorna todos os dados do pedido pelo código", tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Sucesso, retorna dados do pedido", content=@Content(schema = @Schema(example = PedidoResponseSwagger.GETBYID))),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})		
	public ResponseEntity<PedidoDto> detalhar(@ParameterObject @PathVariable @NotNull
			                                  @Schema(description = "Código do pedido no sistema", example = "1", required = true)                     
			                                  Long id){
		logger.info("Consultar pedido pelo idPedido: " + id.toString());
		
		PedidoDto pedido = pedidoController.detalhar(id);
		
		return ResponseEntity.ok(pedido);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Cadastra o pedido", description = "Cria o pedido e retorna o registro cadastrado", tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Sucesso, pedido cadastrado", content=@Content(schema = @Schema(example = PedidoResponseSwagger.CREATED))),
			@ApiResponse(responseCode = "400", description = "Falha, não cadastra o pedido falta informação ou está errada",
			             content=@Content(schema = @Schema(example = PedidoResponseSwagger.BADREQUEST))),
		 	@ApiResponse(responseCode = "401", description = "Não autorizado, cliente não está logado na aplicação para fazer o pedido",
			             content=@Content(schema = @Schema(example = PedidoResponseSwagger.UNAUTHORIZED))),						 
			@ApiResponse(responseCode = "404", description = "Falha, produto ou cliente não encontrado", content=@Content(schema = @Schema(hidden = true)))			
	})					
	public ResponseEntity<PedidoDto> cadastrar(@RequestBody @Valid @Schema(example = PedidoResponseSwagger.POST) PedidoDto dto, UriComponentsBuilder uriBuilder){
		logger.info("Cadastrar pedido");
		
		PedidoDto pedido = pedidoController.cadastrar(dto);		
		preferenciaExternoController.criar(pedido);
		pedido = pedidoController.detalhar(pedido.idPedido());
		
		URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.idPedido()).toUri();
		
		return ResponseEntity.created(endereco).body(pedido);
	}
	
	@PutMapping(value = "/cancelamento/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Cancela o pedido", description = "Cancela o pedido", tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucesso, pedido cancelado", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public ResponseEntity<PedidoDto> cancelar(@ParameterObject @PathVariable @NotNull
			                                  @Schema(description = "Código do pedido no sistema", example = "1", required = true)
			                                  Long id){
		logger.info("Cancelar pedido pelo idPedido: " + id.toString());
		
		pedidoController.cancelar(id);		
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/preparar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Inicia preparação do pedido", description = "Inicia preparação do pedido", tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucesso, iniciada preparação do pedido", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "400", description = "Falha, pedido não está com status de recebido ou não foi pago", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public ResponseEntity<PedidoDto> preparar(@ParameterObject @PathVariable @NotNull
			                                  @Schema(description = "Código do pedido no sistema", example = "1", required = true)
			                                  Long id){
		logger.info("Iniciar preparação do pedido pelo idPedido: " + id.toString());
		
		pedidoController.preparar(id);		
		
		return ResponseEntity.noContent().build();
	}	
	
	@PutMapping(value = "/entregar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Entrega do pedido", description = "Entrega do pedido", tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucesso, entrega o pedido", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "400", description = "Falha, pedido não está com status de preparação ou não foi pago", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public ResponseEntity<PedidoDto> entregar(@ParameterObject @PathVariable @NotNull
			                                  @Schema(description = "Código do pedido no sistema", example = "1", required = true)
			                                  Long id){
		logger.info("Entrega do pedido pelo idPedido: " + id.toString());
		
		pedidoController.entregar(id);		
		
		return ResponseEntity.noContent().build();
	}	
	
	@PutMapping(value = "/finalizar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Finaliza o pedido", description = "Finaliza o pedido", tags = {"Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucesso, finaliza o pedido", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "400", description = "Falha, pedido não está com status de pronto ou não foi pago", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public ResponseEntity<PedidoDto> finalizar(@ParameterObject @PathVariable @NotNull
			                                   @Schema(description = "Código do pedido no sistema", example = "1", required = true)
			                                   Long id){
		logger.info("Finaliza o pedido pelo idPedido: " + id.toString());
		
		pedidoController.finalizar(id);		
		
		return ResponseEntity.noContent().build();
	}		
}
