package br.com.fiap.tiulanches.adapter.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tiulanches.core.entitie.pedido.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
}
