package br.com.fiap.tiulanches.core.domain.dto;

import br.com.fiap.tiulanches.core.domain.entities.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;

public record ClienteDto(
		@Schema(description = "CPF do cliente sem formatação", example = "70636213005", required = true, maxLength = 11)
		String cpf, 
		@Schema(description = "Nome do cliente", example = "Luis Antonio", required = true, maxLength = 60)
		String nome, 
		@Schema(description = "Email do cliente", example = "luisantonio@gmail.com", required = true, maxLength = 60)
		String email) {
	
	public ClienteDto(Cliente cliente) {
		this(cliente.getCpf(), cliente.getNome(), cliente.getEmail());
	}
}
