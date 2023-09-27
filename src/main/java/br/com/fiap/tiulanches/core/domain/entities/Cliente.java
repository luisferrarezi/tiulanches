package br.com.fiap.tiulanches.core.domain.entities;

import br.com.fiap.tiulanches.adapter.infra.annotation.Cpf;
import br.com.fiap.tiulanches.core.domain.dto.ClienteDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	@Size(max=11)
	@Cpf
	private String cpf;
	
	@NotBlank
	@Size(max=60)
	private String nome;
	
	@NotBlank
	@Size(max=60)
	private String email;
	
	public void atualizar(ClienteDto cliente) {
		if (cliente.nome() != null) {
			this.nome = cliente.nome();
		}		
		
		if (cliente.email() != null) {
			this.email = cliente.email();
		}			
	}
	
	public void criar(ClienteDto cliente) {
		this.cpf = cliente.cpf();
		this.nome = cliente.nome();				
		this.email = cliente.email();					
	}	
}
