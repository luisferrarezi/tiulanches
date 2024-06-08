package br.com.fiap.tiulanches.adapter.repository.cliente;

import br.com.fiap.tiulanches.core.entity.cliente.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;

public record ClienteDto(
		@Schema(description = "CPF do cliente sem formatação", example = "70636213005", required = true, maxLength = 11)
		String cpf, 
		@Schema(description = "Nome do cliente", example = "Luis Antonio", required = true, maxLength = 60)
		String nome, 
		@Schema(description = "Email do cliente", example = "luisantonio@gmail.com", required = true, maxLength = 60)
		String email,
		@Schema(description = "Endereço do cliente", example = "Rua Augustina", required = true, maxLength = 100)
		String endereco,
		@Schema(description = "Número endereço do Cliente", example = "1234", required = true, maxLength = 10)
		String numero,
		@Schema(description = "Bairro do cliente", example = "Santa Luzia", required = true, maxLength = 100)
		String bairro,
		@Schema(description = "Cidade do cliente", example = "Potirendaba", required = true, maxLength = 100)
		String cidade,
		@Schema(description = "Estado cidade do cliente", example = "SP", required = true, maxLength = 2)
		String estado,
		@Schema(description = "CEP do cliente", example = "11111-000", required = true, maxLength = 9)
		String cep,
		@Schema(description = "Telefone do cliente", example = "(11) 99999-9999", required = true, maxLength = 15)
		String telefone) {
	
	public ClienteDto(Cliente cliente) {
		this(cliente.getCpf(), cliente.getNome(), cliente.getEmail(), cliente.getEndereco(), cliente.getNumero(), 
			 cliente.getBairro(), cliente.getCidade(), cliente.getEstado(), cliente.getCep(), cliente.getTelefone());
	}
}
