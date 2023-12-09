package br.com.fiap.tiulanches.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tiulanches.core.entitie.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{
}
