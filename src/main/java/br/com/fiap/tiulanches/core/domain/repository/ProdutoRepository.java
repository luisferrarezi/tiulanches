package br.com.fiap.tiulanches.core.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.tiulanches.core.domain.entities.Produto;
import br.com.fiap.tiulanches.core.domain.enums.Categoria;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@SuppressWarnings("el-syntax")
	@Query(value = "SELECT * FROM produtos WHERE categoria = :#{#categoria?.ordinal()}", nativeQuery = true)
	List<Produto> findByCategoria(@Param("categoria") Categoria categoria);
}
