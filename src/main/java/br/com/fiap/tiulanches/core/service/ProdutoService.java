package br.com.fiap.tiulanches.core.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.adapter.controller.ProdutoController;
import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.produto.ProdutoMessage;
import br.com.fiap.tiulanches.core.entity.produto.Produto;
import br.com.fiap.tiulanches.core.enums.Categoria;
import br.com.fiap.tiulanches.core.exception.BusinessException;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService implements ProdutoController {
	private final ProdutoRepository repository; 
	private final ProdutoMessage produtoMensagem;

	public ProdutoService(ProdutoRepository repository, ProdutoMessage produtoMensagem) {
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
		produto.registrar(dto);
		repository.save(produto);
		ProdutoDto produtoDto = new ProdutoDto(produto);

		produtoMensagem.enviaMensagem(EventoEnum.CREATE, produtoDto);
		return produtoDto;
	}
	
	@Transactional	
	public ProdutoDto alterar(Long id, ProdutoDto dto){
		Produto produto = repository.findById(id).orElseThrow(EntityNotFoundException::new);
		produto.registrar(dto);

		ProdutoDto produtoDto = new ProdutoDto(produto);
		produtoMensagem.enviaMensagem(EventoEnum.UPDATE, produtoDto);
		
		return produtoDto;
	}	
	
	public void excluir(Long id){
		Produto produto = repository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		if (produto.isPossuiPedido()){
			throw new BusinessException("Produto já utilizado em pedido, não pode ser excluído!", HttpStatus.BAD_REQUEST, "Produto");
		}

		repository.deleteById(produto.getIdProduto());		
		produtoMensagem.enviaMensagem(EventoEnum.DELETE, new ProdutoDto(produto));
	}	
}