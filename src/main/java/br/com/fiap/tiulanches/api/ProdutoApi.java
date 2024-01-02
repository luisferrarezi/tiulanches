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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.adapter.controller.ProdutoController;
import br.com.fiap.tiulanches.core.enums.Categoria;
import br.com.fiap.tiulanches.infra.swagger.ProdutoResponseSwagger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoApi {

	private final ProdutoController controller;
	
	public ProdutoApi(ProdutoController controller) {
		this.controller = controller;
	};
	
	private static Logger logger = LoggerFactory.getLogger(ProdutoApi.class);
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)	
	@Operation(summary = "Lista todos os produtos", description = "Retorno paginado, padrão de 10 registros por página", tags = {"Produto"})
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Sucesso, lista todos os produtos em paginação")
	})		
	public ResponseEntity<Page<ProdutoDto>> consultar(@ParameterObject @PageableDefault(size=10) Pageable paginacao){
		logger.info("Consultar produtos");
		
		Page<ProdutoDto> page = controller.consultaPaginada(paginacao);
		
		return ResponseEntity.ok(page);
	}	
	
	@GetMapping(value = "/categoria/{categoria}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Lista produtos por categoria", description = "Lista não paginada dos produtos por categoria", tags = {"Produto"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, lista todos os produtos pela categoria selecionada sem paginação")
	})			
	public List<ProdutoDto> consultarByCategoria(@ParameterObject @PathVariable @NotNull
			                                     @Schema(implementation = Categoria.class, description = "Categoria do produto", example = "LANCHE", required = true)
			                                     Categoria categoria){
		logger.info("Consultar produtos pela categoria: " + categoria.toString());
		
		return controller.consultaByCategoria(categoria);
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Retorna dados do produto", description = "Retorna todos os dados do produto pelo código", tags = {"Produto"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, retorna dados do produto"),
			@ApiResponse(responseCode = "404", description = "Falha, produto não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})		
	public ResponseEntity<ProdutoDto> detalhar(@ParameterObject @PathVariable @NotNull
			                                   @Schema(description = "Código do produto no sistema", example = "1", required = true)
			                                   Long id){
		logger.info("Consultar produto pelo idProduto: " + id.toString());
		
		ProdutoDto produto = controller.detalhar(id);
		
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Cadastra o produto", description = "Cria o produto e retorna o registro cadastrado", tags = {"Produto"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Sucesso, produto cadastrado", content=@Content(schema = @Schema(example = ProdutoResponseSwagger.CREATED))),
			@ApiResponse(responseCode = "400", description = "Falha, não cadastra o produto, falta informação ou está errada", content=@Content(schema = @Schema(example = ProdutoResponseSwagger.BADREQUEST)))
	})				
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid @Schema(example = ProdutoResponseSwagger.POST) ProdutoDto dto, UriComponentsBuilder uriBuilder){
		logger.info("Cadastrar produto");
		
		ProdutoDto produto = controller.cadastrar(dto);
		URI endereco = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.idProduto()).toUri();
		
		return ResponseEntity.created(endereco).body(produto);
	}
	
	@PutMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Altera o produto", description = "Altera o produto e retorna o cadastro dele", tags = {"Produto"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, produto alterado", content=@Content(schema = @Schema(example = ProdutoResponseSwagger.UPDATED))),
			@ApiResponse(responseCode = "404", description = "Falha, produto não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})					
	public ResponseEntity<ProdutoDto> alterar(@ParameterObject @PathVariable @NotNull
			                                  @Schema(description = "Código do produto no sistema", example = "8", required = true)
			                                  Long id, @RequestBody @Valid @Schema(example = ProdutoResponseSwagger.PUT) ProdutoDto dto){
		logger.info("Alterar produto pelo idProduto: " + id.toString());
		
		ProdutoDto produto = controller.alterar(id, dto);		
		
		return ResponseEntity.ok(produto);
	}	
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Exclui o produto", description = "Exclui o produto", tags = {"Produto"})	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucesso, produto apagado", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "400", description = "Falha, produto vínculado a um pedido", content=@Content(schema = @Schema(example = ProdutoResponseSwagger.BADREQUESTVINCULADOPEDIDO))),
			@ApiResponse(responseCode = "404", description = "Falha, produto não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})	
	public ResponseEntity<ProdutoDto> excluir(@ParameterObject @PathVariable @NotNull
			                                  @Schema(description = "Código do produto no sistema", example = "1", required = true)
			                                  Long id){
		logger.info("Excluir produto pelo idProduto: " + id.toString());
		
		controller.excluir(id);		
		
		return ResponseEntity.noContent().build();
	}
}
