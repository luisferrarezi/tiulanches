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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.tiulanches.core.domain.service.ProdutoService;
import br.com.fiap.tiulanches.adapter.infra.swagger.ProdutoResponseSwagger;
import br.com.fiap.tiulanches.core.domain.dto.ProdutoDto;
import br.com.fiap.tiulanches.core.domain.enums.Categoria;
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
@RequestMapping(value = "/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	private static Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)	
	@Operation(summary = "Lista todos os produtos cadastrados", 
    		   description = "O retorno é paginado e o padrão são 10 registros por página", 
    		   tags = {"Produto"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, lista todos os produtos em paginação")
	})		
	public ResponseEntity<Page<ProdutoDto>> consultar(@ParameterObject @PageableDefault(size=10) Pageable paginacao){
		logger.info("Consultar produtos");
		
		Page<ProdutoDto> page = service.consultaProdutos(paginacao);
		
		return ResponseEntity.ok(page);
	}	
	
	@GetMapping(value = "/categoria/{categoria}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Lista todos os produtos cadastrados por categoria", 
	           description = "Retorna uma lista não paginada dos produtos de acordo com a categoria informada", 
	           tags = {"Produto"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, lista todos os produtos pela categoria selecionada sem paginação")
	})			
	public List<ProdutoDto> consultarByCategoria(@ParameterObject 
			                                     @PathVariable 
			                                     @NotNull
			                                     @Schema(implementation = Categoria.class, description = "Categoria do produto", example = "LANCHE", required = true)
			                                     Categoria categoria){
		logger.info("Consultar produtos pela categoria: " + categoria.toString());
		
		return service.consultaProdutoByCategoria(categoria);
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Retorna dados do produto", 
	   		   description = "Retorna todos os dados do produto do código informado", 
	   		   tags = {"Produto"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso, retorna dados do produto"),
			@ApiResponse(responseCode = "404", description = "Falha, produto não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})		
	public ResponseEntity<ProdutoDto> detalhar(@ParameterObject 
			                                   @PathVariable 
			                                   @NotNull
			                                   @Schema(description = "Código do produto após ser criado", example = "1", required = true)
			                                   Long id){
		logger.info("Consultar produto pelo idProduto: " + id.toString());
		
		ProdutoDto produto = service.consultaProdutoById(id);
		
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Cadastra o produto", 
	   		   description = "Cria o produto e retorna o registro cadastrado", 
	   		   tags = {"Produto"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", 
					     description = "Sucesso, produto cadastrado", 
					     content=@Content(schema = @Schema(example = ProdutoResponseSwagger.CREATED))),
			@ApiResponse(responseCode = "400", 
			             description = "Falha, não cadastra o produto por faltar informação ou com informação errada",
			             content=@Content(schema = @Schema(example = ProdutoResponseSwagger.BADREQUEST)))
	})				
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid @Schema(example = ProdutoResponseSwagger.POST) ProdutoDto dto, UriComponentsBuilder uriBuilder){
		logger.info("Incluir produto");
		
		ProdutoDto produto = service.criarProduto(dto);
		URI endereco = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.idProduto()).toUri();
		
		return ResponseEntity.created(endereco).body(produto);
	}
	
	@PutMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Altera dados do produto", 
	   		   description = "Altera dados do produto e retorna eles", 
	   		   tags = {"Produto"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", 
					     description = "Sucesso, altera os dados do produto e retorna eles",
					     content=@Content(schema = @Schema(example = ProdutoResponseSwagger.UPDATED))),
			@ApiResponse(responseCode = "404", description = "Falha, produto não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})				
	public ResponseEntity<ProdutoDto> alterar(@ParameterObject 
			                                  @PathVariable 
			                                  @NotNull
			                                  @Schema(description = "Código do produto após ser criado", example = "8", required = true)
			                                  Long id, @RequestBody @Valid @Schema(example = ProdutoResponseSwagger.PUT) ProdutoDto dto){
		logger.info("Alterar produto pelo idProduto: " + id.toString());
		
		ProdutoDto produtoAlterado = service.alterarProduto(id, dto);		
		
		return ResponseEntity.ok(produtoAlterado);
	}	
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Exclui dados do produto", 
			   description = "Exclui dados do produto", 
			   tags = {"Produto"})	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucesso, produto apagado", content=@Content(schema = @Schema(hidden = true))),
			@ApiResponse(responseCode = "404", description = "Falha, produto não encontrado", content=@Content(schema = @Schema(hidden = true)))
	})	
	public ResponseEntity<ProdutoDto> excluir(@ParameterObject 
			                                  @PathVariable 
			                                  @NotNull
			                                  @Schema(description = "Código do produto após ser criado", example = "1", required = true)
			                                  Long id){
		logger.info("Excluir produto pelo idProduto: " + id.toString());
		
		service.excluirProduto(id);		
		
		return ResponseEntity.noContent().build();
	}		
}
