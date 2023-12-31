package br.com.fiap.tiulanches.adapter.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;

public interface ClienteController {	
	public Page<ClienteDto> consultaPaginada(Pageable paginacao);
	public ClienteDto detalhar(String cpf);	
	public ClienteDto cadastrar(ClienteDto dto);	
	public ClienteDto alterar(String cpf, ClienteDto dto);
	public void excluir(String cpf);
}
