package br.com.fiap.tiulanches.adapter.driven.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tiulanches.core.domain.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
}
