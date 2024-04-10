package br.com.fiap.tiulanches.adapter.repository.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.tiulanches.core.enums.StatusPedido;
import br.com.fiap.tiulanches.core.entity.pedido.Pedido;
import br.com.fiap.tiulanches.core.enums.Pago;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	@Query(value = "SELECT pe.* " + 
                   "  FROM pedidos pe " +
                   " INNER JOIN pagamentos pa on pa.id_pagamento = pe.id_pagamento AND pa.pago = :#{#pago?.ordinal()} " +
                   " WHERE pe.status = :#{#status?.ordinal()} ", nativeQuery = true)
	List<Pedido> findByStatusPago(@Param("status") StatusPedido status, @Param("pago") Pago pago);
}
