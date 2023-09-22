package br.com.fiap.tiulanches.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tiulanches.core.domain.entities.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
}
