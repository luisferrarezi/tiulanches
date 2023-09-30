package br.com.fiap.tiulanches.adapter.driver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.tiulanches.adapter.driven.service.ClienteService;
import br.com.fiap.tiulanches.adapter.infra.exception.ErroValidacao;
import br.com.fiap.tiulanches.core.domain.dto.ClienteDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.net.URI;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;	
	
	private static Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	@GetMapping	
	public ResponseEntity<Page<ClienteDto>> consultar(@PageableDefault(size=10) Pageable paginacao){
		logger.info("Consultar clientes");
		
		Page<ClienteDto> page = service.consultaClientes(paginacao); 
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<ClienteDto> detalhar(@PathVariable @NotNull String cpf){
		logger.info("Consultar cliente pelo CPF: " + cpf);
		
		ClienteDto cliente = service.consultaClienteByCpf(cpf);
		
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid ClienteDto dto, UriComponentsBuilder uriBuilder){
		logger.info("Incluir cliente");
		
		if (dto.cpf() != null) {
			ClienteDto cliente = service.criarCliente(dto);
			URI endereco = uriBuilder.path("/clientes/{cpf}").buildAndExpand(cliente.cpf()).toUri();
			return ResponseEntity.created(endereco).body(cliente);
		} else {
			ErroValidacao erro = new ErroValidacao("CPF", "CPF não informado!");
			
			return ResponseEntity.badRequest().body(erro);
		}
	}
	
	@PutMapping("/{cpf}")
	public ResponseEntity<ClienteDto> alterar(@PathVariable @NotNull String cpf, @RequestBody @Valid ClienteDto dto){
		logger.info("Alterar cliente pelo CPF: " + cpf);
		
		ClienteDto clienteAlterado = service.alterarCliente(cpf, dto);		
		
		return ResponseEntity.ok(clienteAlterado);
	}	
	
	@DeleteMapping("/{cpf}")
	public ResponseEntity<ClienteDto> excluir(@PathVariable @NotNull String cpf){
		logger.info("Excluir cliente pelo CPF: " + cpf);
		
		service.excluirCliente(cpf);		
		
		return ResponseEntity.noContent().build();
	}		
}
