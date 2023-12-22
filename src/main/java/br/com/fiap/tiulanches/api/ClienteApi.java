package br.com.fiap.tiulanches.api;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
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

import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.adapter.controller.ClienteController;
import br.com.fiap.tiulanches.infra.swagger.ClienteResponseSwagger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteApi {	
	
	private final ClienteController controller;
	
	public ClienteApi(ClienteController controller) {
		this.controller = controller;
	};
	
	private static Logger logger = LoggerFactory.getLogger(ClienteApi.class);
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)	
	@Operation(summary = "Lista todos os clientes", description = "Retorno paginado, padrão de 10 registros por página", tags = {"Cliente"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, lista todos os clientes em paginação")
	})	
	public ResponseEntity<Page<ClienteDto>> consultar(@ParameterObject @PageableDefault(size=10) Pageable paginacao){
		logger.info("Consultar clientes");		
				
		Page<ClienteDto> page = controller.consultaPaginada(paginacao);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Retorna dados do cliente", description = "Retorna todos os dados do cliente pelo CPF", tags = {"Cliente"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, retorna dados do cliente"),
			@ApiResponse(responseCode = "404", description = "Falha, cliente não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})		
	public ResponseEntity<ClienteDto> detalhar(@ParameterObject @PathVariable @NotNull 
			                                   @Schema(description = "CPF do cliente sem formatação", example = "70636213005", required = true, maxLength = 11) 
											   String cpf){
		logger.info("Consultar cliente pelo CPF: " + cpf);
		
		ClienteDto cliente = controller.detalhar(cpf);
		
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Cadastra o cliente", description = "Cria o cliente e retorna o cadastro dele", tags = {"Cliente"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Sucesso, cliente cadastrado", content=@Content(schema = @Schema(example = ClienteResponseSwagger.CREATED))),
			@ApiResponse(responseCode = "400", description = "Falha, não cadastra o cliente, falta informação ou está errada", content=@Content(schema = @Schema(example = ClienteResponseSwagger.BADREQUEST)))
	})			
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid ClienteDto dto, UriComponentsBuilder uriBuilder){
		logger.info("Cadastrar cliente");
		
		ClienteDto cliente = controller.cadastrar(dto);
		URI endereco = uriBuilder.path("/clientes/{cpf}").buildAndExpand(cliente.cpf()).toUri();
		return ResponseEntity.created(endereco).body(cliente);		
	}
	
	@PutMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Altera o cliente", description = "Altera o cliente e retorna o cadastro dele", tags = {"Cliente"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, cliente alterado", content=@Content(schema = @Schema(example = ClienteResponseSwagger.UPDATED))),
			@ApiResponse(responseCode = "404", description = "Falha, cliente não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})			
	public ResponseEntity<ClienteDto> alterar(@ParameterObject @PathVariable @NotNull
			    							  @Schema(description = "CPF do cliente sem formatação", example = "70636213005", required = true, maxLength = 11)
			    							  String cpf, @RequestBody @Valid @Schema(example = ClienteResponseSwagger.PUT)  ClienteDto dto){
		logger.info("Alterar cliente pelo CPF: " + cpf);
		
		ClienteDto clienteAlterado = controller.alterar(cpf, dto);		
		
		return ResponseEntity.ok(clienteAlterado);
	}	
	
	@DeleteMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Exclui o cliente", description = "Exclui o cliente", tags = {"Cliente"})	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucesso, cliente apagado", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "400", description = "Falha, cliente vínculado a um pedido", content=@Content(schema = @Schema(example = ClienteResponseSwagger.BADREQUESTVINCULADOPEDIDO))),
			@ApiResponse(responseCode = "404", description = "Falha, cliente não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})
	public ResponseEntity<ClienteDto> excluir(@ParameterObject @PathVariable @NotNull
			  								  @Schema(description = "CPF do cliente sem formatação", example = "70636213005", required = true, maxLength = 11)
			  								  String cpf){
		logger.info("Excluir cliente pelo CPF: " + cpf);
		
		controller.excluir(cpf);		
		
		return ResponseEntity.noContent().build();
	}	
}
