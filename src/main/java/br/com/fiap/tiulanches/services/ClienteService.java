package br.com.fiap.tiulanches.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.dtos.ClienteDto;
import br.com.fiap.tiulanches.entities.Cliente;
import br.com.fiap.tiulanches.repositorys.ClienteRepository;
import br.com.fiap.tiulanches.infra.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Page<ClienteDto> consultaClientes(Pageable paginacao){
		return repository.findAll(paginacao).map(ClienteDto::new);
	}
	
	public ClienteDto consultaClienteByCpf(String cpf) {
		Cliente cliente = repository.findById(cpf).orElseThrow(() -> new EntityNotFoundException());

        return new ClienteDto(cliente);
    }
	
	public ClienteDto criarCliente(ClienteDto dto){
		if (dto.cpf() == null) {
			throw new BusinessException("CPF não informado!", HttpStatus.BAD_REQUEST, dto);
		}		
		
		if (repository.findById(dto.cpf()).isEmpty()) {
			Cliente cliente = new Cliente();
			cliente.criar(dto);
			repository.save(cliente);
			return new ClienteDto(cliente);
		} else {
			throw new BusinessException("Cliente já cadastrado", HttpStatus.BAD_REQUEST, dto);
		}
	}
	
	@Transactional
	public ClienteDto alterarCliente(String cpf, ClienteDto dto){
		Cliente cliente = repository.findById(cpf).orElseThrow(() -> new EntityNotFoundException());
		cliente.atualizar(dto);
		
		return new ClienteDto(cliente);
	}	
	
	public void excluirCliente(String cpf){
		Cliente cliente = repository.findById(cpf).orElseThrow(() -> new EntityNotFoundException());
		
		repository.deleteById(cliente.getCpf());
	}	
}
