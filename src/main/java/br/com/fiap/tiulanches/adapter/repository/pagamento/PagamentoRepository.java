package br.com.fiap.tiulanches.adapter.repository.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.tiulanches.core.entitie.pagamento.Pagamento;

public interface PagamentoRepository extends JpaRepository <Pagamento, Long>{	
}
