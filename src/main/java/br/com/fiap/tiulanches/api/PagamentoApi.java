package br.com.fiap.tiulanches.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.tiulanches.adapter.controller.PagamentoController;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/pagamento")
public class PagamentoApi {
	
	private final PagamentoController controller;	
	
	public PagamentoApi(PagamentoController controller) {
		this.controller = controller;
	}
	
	private static Logger logger = LoggerFactory.getLogger(PagamentoApi.class);	
	
	@GetMapping(value = "/status/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Busca o status de pagamento do pedido", description = "Busca o status de pagamento do pedido", tags = {"Pagamento"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, retorna o status de pagamento do pedido"),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public ResponseEntity<PagamentoDto> consultaPagamento(@ParameterObject @PathVariable @NotNull
            									 		  @Schema(description = "Código do pedido no sistema", example = "1", required = true)                     
            									 		  Long id){
		logger.info("Consultar status pagamento do pedido: " + id.toString());
		
		PagamentoDto pagamento = controller.consultaPagamento(id);		
		
		return ResponseEntity.ok(pagamento);
	}
}
