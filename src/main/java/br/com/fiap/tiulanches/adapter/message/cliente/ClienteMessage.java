package br.com.fiap.tiulanches.adapter.message.cliente;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;

public interface ClienteMessage {
    public void enviaMensagem(EventoEnum evento, ClienteDto cliente);
}
    	