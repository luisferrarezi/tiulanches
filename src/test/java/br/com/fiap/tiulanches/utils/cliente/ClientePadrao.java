package br.com.fiap.tiulanches.utils.cliente;

import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.core.entity.cliente.Cliente;
import br.com.fiap.tiulanches.core.enums.Logado;

public class ClientePadrao {    

    public Cliente createClient(){
        return new Cliente(ClienteEnum.CPF.getValor(), 
                           ClienteEnum.NOME.getValor(), 
                           ClienteEnum.EMAIL.getValor(), 
                           Logado.NAO, 0);
    }

    public ClienteDto createClientDto(){
        return new ClienteDto(ClienteEnum.CPF.getValor(), 
                              ClienteEnum.NOME.getValor(), 
                              ClienteEnum.EMAIL.getValor(), 
                              Logado.NAO);
    }
}
