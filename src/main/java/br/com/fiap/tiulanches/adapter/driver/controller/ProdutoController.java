package br.com.fiap.tiulanches.adapter.driver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.tiulanches.adapter.driven.service.ProdutoService;
import br.com.fiap.tiulanches.core.domain.dto.ProdutoDto;
import br.com.fiap.tiulanches.core.domain.enums.Categoria;
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
	
	@GetMapping	
	public ResponseEntity<Page<ProdutoDto>> consultar(@PageableDefault(size=10) Pageable paginacao){
		logger.info("Consultar produtos");
		
		Page<ProdutoDto> page = service.consultaProdutos(paginacao);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping
	@RequestMapping(value = "/categoria")	
	public List<ProdutoDto> consultarByCategoria(@RequestParam Categoria categoria){
		logger.info("Consultar produtos pela categoria: " + categoria.toString());
		
		return service.consultaProdutoByCategoria(categoria);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> detalhar(@PathVariable @NotNull Long id){
		logger.info("Consultar produto pelo idProduto: " + id.toString());
		
		ProdutoDto produto = service.consultaProdutoById(id);
		
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoDto dto, UriComponentsBuilder uriBuilder){
		logger.info("Incluir produto");
		
		ProdutoDto produto = service.criarProduto(dto);
		URI endereco = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.idProduto()).toUri();
		
		return ResponseEntity.created(endereco).body(produto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> alterar(@PathVariable @NotNull Long id, @RequestBody @Valid ProdutoDto dto){
		logger.info("Alterar produto pelo idProduto: " + id.toString());
		
		ProdutoDto produtoAlterado = service.alterarProduto(id, dto);		
		
		return ResponseEntity.ok(produtoAlterado);
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProdutoDto> excluir(@PathVariable @NotNull Long id){
		logger.info("Excluir produto pelo idProduto: " + id.toString());
		
		service.excluirProduto(id);		
		
		return ResponseEntity.noContent().build();
	}		
}
