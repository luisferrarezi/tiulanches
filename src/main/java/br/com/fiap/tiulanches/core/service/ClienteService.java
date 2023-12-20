package br.com.fiap.tiulanches.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.adapter.controller.ClienteController;
import br.com.fiap.tiulanches.core.entitie.cliente.Cliente;
import br.com.fiap.tiulanches.core.exception.BusinessException;
import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService implements ClienteController {
	
	private final ClienteRepository repository;
	
	@Autowired
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	};
	
	public Page<ClienteDto> consultaPaginada(Pageable paginacao){
		return repository.findAll(paginacao).map(ClienteDto::new);
	}
	
	public ClienteDto detalhar(String cpf) {
		Cliente cliente = repository.findById(cpf).orElseThrow(() -> new EntityNotFoundException());

        return new ClienteDto(cliente);
    }
	
	public ClienteDto cadastrar(ClienteDto dto){
		if (dto.cpf() == null) {
			throw new BusinessException("CPF não informado!", HttpStatus.BAD_REQUEST, new String("CPF"));
		}		
		
		if (repository.findById(dto.cpf()).isEmpty()) {
			Cliente cliente = new Cliente();
			cliente.cadastrar(dto);
			
			repository.save(cliente);
			return new ClienteDto(cliente);
		} else {
			throw new BusinessException("Cliente já cadastrado", HttpStatus.BAD_REQUEST, new String("Cliente"));
		}
	}
	
	@Transactional
	public ClienteDto alterar(String cpf, ClienteDto dto){
		Cliente cliente = repository.findById(cpf).orElseThrow(() -> new EntityNotFoundException());
		cliente.atualizar(dto);
		
		return new ClienteDto(cliente);
	}	
	
	public void excluir(String cpf){
		Cliente cliente = repository.findById(cpf).orElseThrow(() -> new EntityNotFoundException());
		
		repository.deleteById(cliente.getCpf());
	}	
}
