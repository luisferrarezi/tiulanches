package br.com.fiap.tiulanches.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import br.com.fiap.tiulanches.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.adapter.ProdutoGateway;
import br.com.fiap.tiulanches.core.enums.Categoria;
import br.com.fiap.tiulanches.core.usecase.ProdutoUseCase;

import java.util.List;

@Controller
public class ProdutoController implements ProdutoGateway {
	
	private final ProdutoUseCase useCase;
	
	@Autowired
	public ProdutoController(ProdutoUseCase useCase) {
		this.useCase = useCase;
	};
	
	public Page<ProdutoDto> consultaPaginada(Pageable paginacao){		
		Page<ProdutoDto> page = useCase.consultaPaginada(paginacao);
		
		return page;
	}
	
	public List<ProdutoDto> consultaByCategoria(Categoria categoria){
		return useCase.consultaByCategoria(categoria);
	}
	
	public ProdutoDto detalhar(Long id){
		return useCase.consultaById(id);
	}
	
	public ProdutoDto cadastrar(ProdutoDto dto){
		return useCase.cadastrar(dto);
	}
	
	public ProdutoDto alterar(Long id, ProdutoDto dto){
		return useCase.alterar(id, dto);
	}	
	
	public void excluir(Long id){
		useCase.excluir(id);
	}		
}
