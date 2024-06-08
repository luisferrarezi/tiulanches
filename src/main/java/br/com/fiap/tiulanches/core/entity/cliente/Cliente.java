package br.com.fiap.tiulanches.core.entity.cliente;

import br.com.fiap.tiulanches.core.annotation.Cpf;
import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Cliente")
@Table(name = "CLIENTES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "cpf")
public class Cliente {
	@Id	
	@Size(max=11, min=11)
	@Cpf
	@Schema(description = "CPF do cliente sem formatação", example = "70636213005", required = true, maxLength = 11, minLength = 11)
	private String cpf;
	
	@NotBlank
	@Size(max=60)
	@Schema(description = "Nome do cliente", example = "Luis Antonio", required = true, maxLength = 60)
	private String nome;
	
	@NotBlank
	@Email
	@Size(max=60)
	@Schema(description = "Email do cliente", example = "luisantonio@gmail.com", required = true, maxLength = 60)
	private String email;

	@NotBlank
	@Size(max=100)
	@Schema(description = "Endereço do cliente", example = "Rua Augustina", required = true, maxLength = 100)
	private String endereco;

	@NotBlank
	@Size(max=10)
	@Schema(description = "Número endereço do Cliente", example = "1234", required = true, maxLength = 10)
	private String numero;

	@NotBlank
	@Size(max=100)
	@Schema(description = "Bairro do cliente", example = "Santa Luzia", required = true, maxLength = 100)
	private String bairro;

	@NotBlank
	@Size(max=100)
	@Schema(description = "Cidade do cliente", example = "Potirendaba", required = true, maxLength = 100)
	private String cidade;

	@NotBlank
	@Size(max=2)
	@Schema(description = "Estado cidade do cliente", example = "SP", required = true, maxLength = 2)
	private String estado;

	@NotBlank
	@Size(max=9)
	@Schema(description = "CEP do cliente", example = "11111-000", required = true, maxLength = 9)
	private String cep;

	@NotBlank
	@Size(max=15)
	@Schema(description = "Telefone do cliente", example = "(11) 99999-9999", required = true, maxLength = 15)
	private String telefone;

	public void atualizar(ClienteDto cliente) {
		validaNome(cliente.nome());
		validaEmail(cliente.email());
		validaEndereco(cliente.endereco());
		validaNumero(cliente.numero());
		validaBairro(cliente.bairro());
		validaCidade(cliente.cidade());
		validaEstado(cliente.estado());
		validaCep(cliente.cep());
		validaTelefone(cliente.telefone());
	}
	
	public void cadastrar(ClienteDto cliente) {
		this.cpf = cliente.cpf();
		validaNome(cliente.nome());				
		validaEmail(cliente.email());
		validaEndereco(cliente.endereco());
		validaNumero(cliente.numero());
		validaBairro(cliente.bairro());
		validaCidade(cliente.cidade());
		validaEstado(cliente.estado());
		validaCep(cliente.cep());
		validaTelefone(cliente.telefone());		
	}
	
	private void validaNome(String nome) {
		if (nome != null) {
			this.nome = nome;
		}				
	}
	
	private void validaEmail(String email) {
		if (email != null) {
			this.email = email;
		}				
	}

	private void validaEndereco(String endereco) {
		if (endereco != null) {
			this.endereco = endereco;
		}				
	}

	private void validaNumero(String numero) {
		if (numero != null) {
			this.numero = numero;
		}				
	}

	private void validaBairro(String bairro) {
		if (bairro != null) {
			this.bairro = bairro;
		}				
	}

	private void validaCidade(String cidade) {
		if (cidade != null) {
			this.cidade = cidade;
		}				
	}

	private void validaEstado(String estado) {
		if (estado != null) {
			this.estado = estado;
		}				
	}

	private void validaCep(String cep) {
		if (cep != null) {
			this.cep = cep;
		}				
	}

	private void validaTelefone(String telefone) {
		if (telefone != null) {
			this.telefone = telefone;
		}				
	}
}
