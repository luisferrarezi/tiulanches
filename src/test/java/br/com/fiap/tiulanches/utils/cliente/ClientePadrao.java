package br.com.fiap.tiulanches.utils.cliente;

import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.core.entity.cliente.Cliente;

public class ClientePadrao {    

    public Cliente createClient(){
        return new Cliente(ClienteEnum.CPF.getValor(), 
                           ClienteEnum.NOME.getValor(), 
                           ClienteEnum.EMAIL.getValor(),
                           ClienteEnum.ENDERECO.getValor(),
                           ClienteEnum.NUMERO.getValor(),
                           ClienteEnum.BAIRRO.getValor(),
                           ClienteEnum.CIDADE.getValor(),
                           ClienteEnum.ESTADO.getValor(),
                           ClienteEnum.CEP.getValor(),
                           ClienteEnum.TELEFONE.getValor());
    }

    public ClienteDto createClientDto(){
        return new ClienteDto(ClienteEnum.CPF.getValor(), 
                              ClienteEnum.NOME.getValor(), 
                              ClienteEnum.EMAIL.getValor(),
                              ClienteEnum.ENDERECO.getValor(),
                              ClienteEnum.NUMERO.getValor(),
                              ClienteEnum.BAIRRO.getValor(),
                              ClienteEnum.CIDADE.getValor(),
                              ClienteEnum.ESTADO.getValor(),
                              ClienteEnum.CEP.getValor(),
                              ClienteEnum.TELEFONE.getValor());
    }
}
