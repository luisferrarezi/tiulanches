package br.com.fiap.tiulanches.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import br.com.fiap.tiulanches.services.ClienteService;
import br.com.fiap.tiulanches.dtos.ClienteDto;

@Controller
public class ClienteController{
	
	@Autowired
	private ClienteService service;
	
	public Page<ClienteDto> consultar(Pageable paginacao){
		Page<ClienteDto> page = service.consultaClientes(paginacao); 
		
		return page;
	}

	public ClienteDto detalhar(String cpf){
		ClienteDto cliente = service.consultaClienteByCpf(cpf);
		
		return cliente;
	}	
	
	public ClienteDto cadastrar(ClienteDto dto){		
		
		
		ClienteDto cliente = service.criarCliente(dto);
		return cliente;		
	}
	
	public ClienteDto alterar(String cpf, ClienteDto dto){				
		ClienteDto clienteDto = service.alterarCliente(cpf, dto);		
		
		return clienteDto;
	}	

	public void excluir(String cpf){
		service.excluirCliente(cpf);
	}		
}
