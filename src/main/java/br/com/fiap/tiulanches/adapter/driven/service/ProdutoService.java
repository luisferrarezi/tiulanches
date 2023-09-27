package br.com.fiap.tiulanches.adapter.driven.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.core.domain.dto.ProdutoDto;
import br.com.fiap.tiulanches.core.domain.entities.Produto;
import br.com.fiap.tiulanches.core.domain.enums.Categoria;
import br.com.fiap.tiulanches.core.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repository;
	
	public Page<ProdutoDto> consultaProdutos(Pageable paginacao){
		return repository.findAll(paginacao).map(ProdutoDto::new);
	}
	
	public List<ProdutoDto> consultaProdutoByCategoria(Categoria categoria){
		List<Produto> listProduto = repository.findByCategoria(categoria);
		
		return listProduto.stream().map(produto -> new ProdutoDto(produto)).collect(Collectors.toList());		
	}
	
	public ProdutoDto consultaProdutoById(Long id) {
        Produto produto = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return new ProdutoDto(produto);
    }
	
	public ProdutoDto criarProduto(ProdutoDto dto){
		Produto produto = new Produto();
		produto.criar(dto);
		repository.save(produto);
		
		return new ProdutoDto(produto);
	}
	
	@Transactional	
	public ProdutoDto alterarProduto(Long id, ProdutoDto dto){
		Produto produto = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		produto.atualizar(dto);
				
		return new ProdutoDto(produto);
	}	
	
	public void excluirProduto(Long id){
		repository.deleteById(id);
	}	
}
