package br.com.fiap.tiulanches.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.adapter.controller.ClienteController;
import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.cliente.ClienteMessage;
import br.com.fiap.tiulanches.core.entity.cliente.Cliente;
import br.com.fiap.tiulanches.core.exception.BusinessException;
import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService implements ClienteController {
	
	private final ClienteRepository repository;
	private final ClienteMessage message;
	
	public ClienteService(ClienteRepository repository, ClienteMessage message) {
		this.repository = repository;
		this.message = message;
	}
	
	public Page<ClienteDto> consultaPaginada(Pageable paginacao){
		return repository.findAll(paginacao).map(ClienteDto::new);
	}
	
	public ClienteDto detalhar(String cpf) {
		Cliente cliente = repository.findById(cpf).orElseThrow(EntityNotFoundException::new);

        return new ClienteDto(cliente);
    }
	
	public ClienteDto cadastrar(ClienteDto dto){
		if (dto.cpf() == null) {
			throw new BusinessException("CPF não informado!", HttpStatus.BAD_REQUEST, "CPF");
		}		
		
		if (repository.findById(dto.cpf()).isEmpty()) {
			Cliente cliente = new Cliente();
			cliente.cadastrar(dto);
			
			repository.save(cliente);

			ClienteDto clienteDto = new ClienteDto(cliente);
			message.enviaMensagem(EventoEnum.CREATE, clienteDto);

			return clienteDto;
		} else {
			throw new BusinessException("Cliente já cadastrado", HttpStatus.BAD_REQUEST, "Cliente");
		}
	}
	
	@Transactional
	public ClienteDto alterar(String cpf, ClienteDto dto){
		Cliente cliente = repository.findById(cpf).orElseThrow(EntityNotFoundException::new);
		cliente.atualizar(dto);
		
		ClienteDto clienteDto = new ClienteDto(cliente);
		message.enviaMensagem(EventoEnum.UPDATE, clienteDto);

		return clienteDto;
	}	
	
	public void excluir(String cpf){
		Cliente cliente = repository.findById(cpf).orElseThrow(EntityNotFoundException::new);
		
		if (cliente.isPossuiPedido()){
			throw new BusinessException("Cliente já utilizado em pedido, não pode ser excluído!", HttpStatus.BAD_REQUEST, "Cliente");
		}

		repository.deleteById(cliente.getCpf());
		message.enviaMensagem(EventoEnum.DELETE, new ClienteDto(cliente));
	}	
}
