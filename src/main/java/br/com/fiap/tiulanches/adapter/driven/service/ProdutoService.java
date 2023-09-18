package br.com.fiap.tiulanches.adapter.driven.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.core.domain.dto.ProdutoDto;
import br.com.fiap.tiulanches.core.domain.entities.Produto;
import br.com.fiap.tiulanches.core.domain.enums.Categoria;
import br.com.fiap.tiulanches.core.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<ProdutoDto> consultaProdutos(Pageable paginacao){
		return repository.findAll(paginacao).map(p -> modelMapper.map(p, ProdutoDto.class));
	}
	
	public List<ProdutoDto> consultaProdutoByCategoria(Categoria categoria){
		List<Produto> listProduto = repository.findByCategoria(categoria);
		
		return listProduto.stream().map(produto -> modelMapper.map(produto, ProdutoDto.class)).collect(Collectors.toList());		
	}
	
	public ProdutoDto consultaProdutoById(Long id) {
        Produto produto = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(produto, ProdutoDto.class);
    }
	
	public ProdutoDto criarProduto(ProdutoDto dto){
		Produto produto = modelMapper.map(dto, Produto.class);
		repository.save(produto);
		
		return modelMapper.map(produto, ProdutoDto.class);
	}
	
	public ProdutoDto alterarProduto(Long id, ProdutoDto dto){
		Produto produto = modelMapper.map(dto, Produto.class);
		produto.setIdProduto(id);
		
		produto = repository.save(produto);
		
		return modelMapper.map(produto, ProdutoDto.class);
	}	
	
	public void excluirProduto(Long id){
		repository.deleteById(id);
	}	
}
