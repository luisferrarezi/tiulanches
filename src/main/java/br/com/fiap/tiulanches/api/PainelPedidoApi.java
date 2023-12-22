package br.com.fiap.tiulanches.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tiulanches.adapter.controller.PainelPedidoController;
import br.com.fiap.tiulanches.adapter.repository.painelpedido.PainelPedidoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/painelpedidos")
public class PainelPedidoApi {

	private final PainelPedidoController controller;
	
	public PainelPedidoApi(PainelPedidoController controller) {
		this.controller = controller;
	}
	
	private static Logger logger = LoggerFactory.getLogger(PainelPedidoApi.class);
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Lista os pedidos com status Recebidos, Em Preparação e Prontos", description = "Lista não paginada os pedidos com status Recebidos, Em Preparação e Prontos", tags = {"Painel Pedido"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, lista os pedidos com status Recebidos, Em Preparação e Prontos sem paginação"),
			@ApiResponse(responseCode = "404", description = "Falha, pedido não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public List<PainelPedidoDto> consultar(){
		logger.info("Consultar pedidos para o painel de pedidos");
		
		return controller.consultaPainelPedido();
	}	
}
