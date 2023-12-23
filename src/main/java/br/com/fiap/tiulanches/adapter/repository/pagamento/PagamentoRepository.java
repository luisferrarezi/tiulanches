package br.com.fiap.tiulanches.adapter.repository.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.tiulanches.core.entitie.pagamento.ConsultaPagamento;

public interface PagamentoRepository extends JpaRepository <ConsultaPagamento, Long>{
	@Query(value = "SELECT id_pedido, pago FROM pagamentos WHERE id_pedido = :#{#id_pedido} ", nativeQuery = true)
	ConsultaPagamento consultaPagamento(@Param("id_pedido") Long pedido);
}
