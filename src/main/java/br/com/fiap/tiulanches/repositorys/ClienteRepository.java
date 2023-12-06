package br.com.fiap.tiulanches.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tiulanches.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{
}
