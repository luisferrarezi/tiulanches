package br.com.fiap.tiulanches.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import br.com.fiap.tiulanches.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.adapter.ClienteGateway;
import br.com.fiap.tiulanches.core.usecase.ClienteUseCase;

@Controller
public class ClienteController implements ClienteGateway {
	
	private final ClienteUseCase useCase;
	
	@Autowired
	public ClienteController(ClienteUseCase useCase) {
		this.useCase = useCase;
	};
	
	public Page<ClienteDto> consultaPaginada(Pageable paginacao){
		Page<ClienteDto> page = useCase.consultaPaginada(paginacao); 
		
		return page;
	}

	public ClienteDto detalhar(String cpf){
		return useCase.consultaByCpf(cpf);
	}	
	
	public ClienteDto cadastrar(ClienteDto dto){
		return useCase.cadastrar(dto);		
	}
	
	public ClienteDto alterar(String cpf, ClienteDto dto){
		return useCase.alterar(cpf, dto);
	}	

	public void excluir(String cpf){
		useCase.excluir(cpf);
	}		
}
