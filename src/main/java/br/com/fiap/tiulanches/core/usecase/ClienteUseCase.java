package br.com.fiap.tiulanches.core.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.fiap.tiulanches.repository.cliente.ClienteDto;

public interface ClienteUseCase {
	public Page<ClienteDto> consultaPaginada(Pageable paginacao);	
	public ClienteDto consultaByCpf(String cpf);	
	public ClienteDto cadastrar(ClienteDto dto);
	public ClienteDto alterar(String cpf, ClienteDto dto);	
	public void excluir(String cpf);
}
