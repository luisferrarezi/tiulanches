package br.com.fiap.tiulanches.adapter.driven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.core.domain.dto.ClienteDto;
import br.com.fiap.tiulanches.core.domain.entities.Cliente;
import br.com.fiap.tiulanches.core.domain.repository.ClienteRepository;
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
		Cliente cliente = new Cliente();
		cliente.criar(dto);
		repository.save(cliente);
		
		return new ClienteDto(cliente);
	}
	
	@Transactional
	public ClienteDto alterarCliente(String cpf, ClienteDto dto){
		Cliente cliente = repository.findById(cpf).orElseThrow(() -> new EntityNotFoundException());
		cliente.atualizar(dto);
		
		return new ClienteDto(cliente);
	}	
	
	public void excluirCliente(String cpf){
		repository.deleteById(cpf);
	}	
}
