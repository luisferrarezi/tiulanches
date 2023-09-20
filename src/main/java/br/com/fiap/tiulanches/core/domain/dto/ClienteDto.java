package br.com.fiap.tiulanches.core.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {
	private String cpf;
	private String nome;
	private String email;
}
