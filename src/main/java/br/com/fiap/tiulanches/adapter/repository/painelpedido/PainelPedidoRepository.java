package br.com.fiap.tiulanches.adapter.repository.painelpedido;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.tiulanches.core.entity.painelpedido.PainelPedido;

public interface PainelPedidoRepository extends JpaRepository <PainelPedido, Long>{
	
	@Query(value = "SELECT pe.id_pedido, pe.status " + 
	               "  FROM pedidos pe " +
	               " INNER JOIN pagamentos pa on pa.id_pagamento = pe.id_pagamento AND pa.pago = :#{#pago} " +
	               " WHERE pe.status IN (:#{#recebido}, :#{#preparacao}, :#{#pronto}) " + 
	               " ORDER BY pe.status DESC, pe.id_pedido ", nativeQuery = true)
	List<PainelPedido> consultaPainelPedido(@Param("recebido") int recebido,
											@Param("preparacao") int preparacao,
											@Param("pronto") int pronto,
											@Param("pago") int pago);
}
