package br.com.fiap.tiulanches.adapter.driver.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.tiulanches.adapter.driven.service.ProdutoService;
import br.com.fiap.tiulanches.core.domain.dto.ProdutoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URI;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public Page<ProdutoDto> consultar(@PageableDefault(size=10) Pageable paginacao){
		return service.consultaProdutoByCategoria(paginacao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> detalhar(@PathVariable @NotNull Long id){
		ProdutoDto produto = service.consultaProdutoById(id);
		
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoDto dto, UriComponentsBuilder uriBuilder){
		ProdutoDto produto = service.criarProduto(dto);
		URI endereco = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getIdProduto()).toUri();
		
		return ResponseEntity.created(endereco).body(produto);
	}
	
	@PutMapping
	public ResponseEntity<ProdutoDto> alterar(@PathVariable @NotNull Long id, @RequestBody @Valid ProdutoDto dto){
		ProdutoDto produtoAlterado = service.alterarProduto(id, dto);		
		
		return ResponseEntity.ok(produtoAlterado);
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProdutoDto> excluir(@PathVariable @NotNull Long id){
		service.excluirProduto(id);		
		
		return ResponseEntity.noContent().build();
	}		
}
