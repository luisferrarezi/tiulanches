package br.com.fiap.tiulanches.repository.pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.tiulanches.core.entitie.pedido.Pedido;
import br.com.fiap.tiulanches.core.enums.StatusPedido;
import br.com.fiap.tiulanches.core.enums.Pago;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@SuppressWarnings("el-syntax")
	@Query(value = "SELECT * FROM pedidos WHERE status = :#{#status?.ordinal()} and pago = :#{#pago?.ordinal()} ", nativeQuery = true)
	List<Pedido> findByStatusPago(@Param("status") StatusPedido status, @Param("pago") Pago pago);
}
