package br.com.fiap.tiulanches.adapter.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tiulanches.core.entity.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{
}
