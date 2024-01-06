package br.com.fiap.tiulanches.webhook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tiulanches.adapter.controller.PagamentoExternoController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/pagamentowb")
public class PagamentoWebHook {
	private final PagamentoExternoController controller;
	
	public PagamentoWebHook(PagamentoExternoController controller) {
		this.controller = controller;
	};
	
	private static Logger logger = LoggerFactory.getLogger(PagamentoWebHook.class);
	
	@PostMapping(value = "/processa", produces = MediaType.APPLICATION_JSON_VALUE)	
	@Operation(summary = "Webhook para receber informação pagamento Mercado Pago", description = "Processa pagamento", tags = {"Webhook"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucesso, pagamento processado", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "400", description = "Falha, erro ao processar pagamento", content=@Content(schema = @Schema(hidden = true))),
	})				
	
	public ResponseEntity<?> processa(@Schema(description = "Código do pagamento realizado no mercado pago", example = "1320380317", required = true)
									  @RequestParam("data.id") long idPagamento,
									  @Schema(description = "Tipo de json enviado pelo mercado pago", example = "payment", required = true)
									  @RequestParam("type") String type) {
		logger.info("Processa pagamento webhook: " + idPagamento);
		
		if (controller.processar(idPagamento, type)) {
	       	return ResponseEntity.noContent().build();
	    } else {
	       	return ResponseEntity.badRequest().body("Erro ao processar pagamento");
	    }	    
	}
}
