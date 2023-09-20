package br.com.fiap.tiulanches.core.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CLIENTES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	@Id	
	@Size(max=11)
	private String cpf;
	
	@NotBlank
	@Size(max=60)
	private String nome;
	
	@NotBlank
	@Size(max=60)
	private String email;
}
