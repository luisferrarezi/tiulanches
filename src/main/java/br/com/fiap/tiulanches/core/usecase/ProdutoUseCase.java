package br.com.fiap.tiulanches.core.usecase;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.fiap.tiulanches.core.enums.Categoria;
import br.com.fiap.tiulanches.repository.produto.ProdutoDto;

public interface ProdutoUseCase {
	public Page<ProdutoDto> consultaPaginada(Pageable paginacao);	
	public List<ProdutoDto> consultaByCategoria(Categoria categoria);	
	public ProdutoDto consultaById(Long id);	
	public ProdutoDto cadastrar(ProdutoDto dto);		
	public ProdutoDto alterar(Long id, ProdutoDto dto);	
	public void excluir(Long id);
}
