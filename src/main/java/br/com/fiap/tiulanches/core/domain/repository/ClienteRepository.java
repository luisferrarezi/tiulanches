package br.com.fiap.tiulanches.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tiulanches.core.domain.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{
}
