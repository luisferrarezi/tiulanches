package br.com.fiap.tiulanches.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.tiulanches.entities.Pedido;
import br.com.fiap.tiulanches.enums.StatusPedido;
import br.com.fiap.tiulanches.enums.Pago;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@SuppressWarnings("el-syntax")
	@Query(value = "SELECT * FROM pedidos WHERE status = :#{#status?.ordinal()} and pago = :#{#pago?.ordinal()} ", nativeQuery = true)
	List<Pedido> findByStatusPago(@Param("status") StatusPedido status, @Param("pago") Pago pago);
}
