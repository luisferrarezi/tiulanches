package br.com.fiap.tiulanches.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.adapter.controller.ProdutoController;
import br.com.fiap.tiulanches.adapter.mensagem.produto.ProdutoMensagem;
import br.com.fiap.tiulanches.core.entitie.produto.Produto;
import br.com.fiap.tiulanches.core.enums.Categoria;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService implements ProdutoController {
	private final ProdutoRepository repository; 
	private final ProdutoMensagem produtoMensagem;

	public ProdutoService(ProdutoRepository repository, ProdutoMensagem produtoMensagem) {
		this.repository = repository;
		this.produtoMensagem = produtoMensagem;
	}
	
	public Page<ProdutoDto> consultaPaginada(Pageable paginacao){
		return repository.findAll(paginacao).map(ProdutoDto::new);
	}
	
	public List<ProdutoDto> consultaByCategoria(Categoria categoria){
		List<Produto> listProduto = repository.findByCategoria(categoria);
		
		return listProduto.stream().map(ProdutoDto::new).collect(Collectors.toList());		
	}
	
	public ProdutoDto detalhar(Long id) {
        Produto produto = repository.findById(id).orElseThrow(EntityNotFoundException::new);

        return new ProdutoDto(produto);
    }
	
	public ProdutoDto cadastrar(ProdutoDto dto){
		Produto produto = new Produto();
		produto.cadastrar(dto);
		repository.save(produto);
		ProdutoDto produtoDto = new ProdutoDto(produto);

		produtoMensagem.enviaMensagem("CREATE", produtoDto);		
		return produtoDto;
	}
	
	@Transactional	
	public ProdutoDto alterar(Long id, ProdutoDto dto){
		Produto produto = repository.findById(id).orElseThrow(EntityNotFoundException::new);
		produto.atualizar(dto);
				
		return new ProdutoDto(produto);
	}	
	
	public void excluir(Long id){
		Produto produto = repository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		repository.deleteById(produto.getIdProduto());
	}	
}