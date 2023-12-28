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

@RestController
@RequestMapping(value = "/pagamentowb")
public class PagamentoWebHook {
	private final PagamentoExternoController controller;
	
	public PagamentoWebHook(PagamentoExternoController controller) {
		this.controller = controller;
	};
	
	private static Logger logger = LoggerFactory.getLogger(PagamentoWebHook.class);
	
	@PostMapping(value = "/processa", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> processa(@RequestParam("data.id") long idPagamento, 
									  @RequestParam("type") String type) {
		logger.info("Processa pagamento webhook: " + idPagamento);
		
		if (controller.processar(idPagamento, type)) {
	       	return ResponseEntity.ok().build();
	    } else {
	       	return ResponseEntity.badRequest().body("Erro ao processar pagamento");
	    }	    
	}
}
