package br.com.fiap.tiulanches.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.adapter.controller.ProdutoController;
import br.com.fiap.tiulanches.core.entitie.produto.Produto;
import br.com.fiap.tiulanches.core.enums.Categoria;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService implements ProdutoController {
	private final ProdutoRepository repository; 
	
	public ProdutoService(ProdutoRepository repository) {
		this.repository = repository;
	}
	
	public Page<ProdutoDto> consultaPaginada(Pageable paginacao){
		return repository.findAll(paginacao).map(ProdutoDto::new);
	}
	
	public List<ProdutoDto> consultaByCategoria(Categoria categoria){
		List<Produto> listProduto = repository.findByCategoria(categoria);
		
		return listProduto.stream().map(produto -> new ProdutoDto(produto)).collect(Collectors.toList());		
	}
	
	public ProdutoDto detalhar(Long id) {
        Produto produto = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return new ProdutoDto(produto);
    }
	
	public ProdutoDto cadastrar(ProdutoDto dto){
		Produto produto = new Produto();
		produto.cadastrar(dto);
		repository.save(produto);
		
		return new ProdutoDto(produto);
	}
	
	@Transactional	
	public ProdutoDto alterar(Long id, ProdutoDto dto){
		Produto produto = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		produto.atualizar(dto);
				
		return new ProdutoDto(produto);
	}	
	
	public void excluir(Long id){
		Produto produto = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		
		repository.deleteById(produto.getIdProduto());
	}	
}