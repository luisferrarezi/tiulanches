package br.com.fiap.tiulanches.core.domain.dto;

import br.com.fiap.tiulanches.core.domain.entities.Cliente;

public record ClienteDto(
		String cpf, 
		String nome, 
		String email) {
	
	public ClienteDto(Cliente cliente) {
		this(cliente.getCpf(), cliente.getNome(), cliente.getEmail());
	}
}
