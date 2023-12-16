package br.com.fiap.tiulanches.adapter.repository.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.fiap.tiulanches.core.entitie.pagamento.Pagamento;

public interface PagamentoRepository extends JpaRepository <Pagamento, Long>{
	@Query(value = "SELECT id_pedido, pago FROM pedidos WHERE id_pedido = :#{#id_pedido} ", nativeQuery = true)
	Pagamento consultaPagamento(@Param("id_pedido") Long pedido);
}
