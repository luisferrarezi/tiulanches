package br.com.fiap.tiulanches.adapter.repository.painelpedido;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.fiap.tiulanches.core.entitie.painelpedido.PainelPedido;

public interface PainelPedidoRepository extends JpaRepository <PainelPedido, Long>{
	
	@Query(value = "SELECT id_pedido, status FROM pedidos WHERE status IN (:#{#recebido}, :#{#preparacao}, :#{#pronto}) AND pago = :#{#pago} ORDER BY status DESC, id_pedido ", nativeQuery = true)
	List<PainelPedido> consultaPainelPedido(@Param("recebido") int recebido,
											@Param("preparacao") int preparacao,
											@Param("pronto") int pronto,
											@Param("pago") int pago);
}
