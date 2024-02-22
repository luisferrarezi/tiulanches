package br.com.fiap.tiulanches.adapter.repository.cliente;

import br.com.fiap.tiulanches.core.entitie.cliente.Cliente;
import br.com.fiap.tiulanches.core.enums.Logado;
import io.swagger.v3.oas.annotations.media.Schema;

public record ClienteDto(
		@Schema(description = "CPF do cliente sem formatação", example = "70636213005", required = true, maxLength = 11)
		String cpf, 
		@Schema(description = "Nome do cliente", example = "Luis Antonio", required = true, maxLength = 60)
		String nome, 
		@Schema(description = "Email do cliente", example = "luisantonio@gmail.com", required = true, maxLength = 60)
		String email,
		@Schema(implementation = Logado.class, description = "Cliente logado", example = "SIM", required = true)
		Logado logado) {
	
	public ClienteDto(Cliente cliente) {
		this(cliente.getCpf(), cliente.getNome(), cliente.getEmail(), cliente.getLogado());
	}
}
