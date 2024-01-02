package br.com.fiap.tiulanches.adapter.repository.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.tiulanches.core.entitie.pagamento.ConsultaPagamento;

public interface ConsultaPagamentoRepository extends JpaRepository <ConsultaPagamento, Long>{
		
	@Query(value = "SELECT pe.id_pedido, pa.pago " 
				 + "  FROM pedidos pe "
				 + " INNER JOIN pagamentos pa on pa.id_pagamento = pe.id_pagamento "
				 + " WHERE id_pedido = :#{#id_pedido} ", nativeQuery = true)
	ConsultaPagamento consultaPagamento(@Param("id_pedido") Long pedido);
}
