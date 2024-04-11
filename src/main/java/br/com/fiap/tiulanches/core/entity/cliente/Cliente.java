package br.com.fiap.tiulanches.core.entity.cliente;

import br.com.fiap.tiulanches.core.annotation.Cpf;
import br.com.fiap.tiulanches.core.enums.Logado;
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

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Schema(implementation = Logado.class, description = "Categoria do produto", example = "LANCHE", required = true)	
	private Logado logado;	
	
	@Schema(description = "Indica se o cliente possuí algum pedido vinculado a ele", example = "0", required = true)
	private int pedidoVinculado;

	public void atualizar(ClienteDto cliente) {
		validaNome(cliente.nome());
		validaEmail(cliente.email());
	}
	
	public void cadastrar(ClienteDto cliente) {
		this.cpf = cliente.cpf();
		validaNome(cliente.nome());				
		validaEmail(cliente.email());
		this.logado = Logado.NAO;
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

	public boolean isLogado(){
		return this.logado == Logado.SIM;
	}

	public boolean isPossuiPedido(){
		return this.pedidoVinculado == 1;
	}	
}
