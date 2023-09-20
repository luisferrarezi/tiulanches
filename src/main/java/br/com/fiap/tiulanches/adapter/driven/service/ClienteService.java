package br.com.fiap.tiulanches.adapter.driven.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.core.domain.dto.ClienteDto;
import br.com.fiap.tiulanches.core.domain.entities.Cliente;
import br.com.fiap.tiulanches.core.domain.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<ClienteDto> consultaClientes(Pageable paginacao){
		return repository.findAll(paginacao).map(p -> modelMapper.map(p, ClienteDto.class));
	}
	
	public ClienteDto consultaClienteByCpf(String cpf) {
		Cliente cliente = repository.findById(cpf).orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(cliente, ClienteDto.class);
    }
	
	public ClienteDto criarCliente(ClienteDto dto){
		Cliente cliente = modelMapper.map(dto, Cliente.class);
		repository.save(cliente);
		
		return modelMapper.map(cliente, ClienteDto.class);
	}
	
	public ClienteDto alterarCliente(String cpf, ClienteDto dto){
		Cliente cliente = modelMapper.map(dto, Cliente.class);
		cliente.setCpf(cpf);
		
		cliente = repository.save(cliente);
		
		return modelMapper.map(cliente, ClienteDto.class);
	}	
	
	public void excluirCliente(String cpf){
		repository.deleteById(cpf);
	}	
}
