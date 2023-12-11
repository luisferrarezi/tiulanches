package br.com.fiap.tiulanches.adapter.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.enums.Categoria;
import java.util.List;

public interface ProdutoController {	
	public Page<ProdutoDto> consultaPaginada(Pageable paginacao);	
	public List<ProdutoDto> consultaByCategoria(Categoria categoria);	
	public ProdutoDto detalhar(Long id);	
	public ProdutoDto cadastrar(ProdutoDto dto);	
	public ProdutoDto alterar(Long id, ProdutoDto dto);	
	public void excluir(Long id);
}
