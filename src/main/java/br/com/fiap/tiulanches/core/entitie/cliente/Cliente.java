package br.com.fiap.tiulanches.core.entitie.cliente;

import br.com.fiap.tiulanches.core.annotation.Cpf;
import br.com.fiap.tiulanches.repository.cliente.ClienteDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
	
	public void atualizar(ClienteDto cliente) {
		validaNome(cliente.nome());
		validaEmail(cliente.email());
	}
	
	public void cadastrar(ClienteDto cliente) {
		this.cpf = cliente.cpf();
		validaNome(cliente.nome());				
		validaEmail(cliente.email());					
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
}
