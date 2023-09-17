package br.com.fiap.tiulanches.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.tiulanches.core.domain.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
