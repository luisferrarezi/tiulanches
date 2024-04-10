package br.com.fiap.tiulanches.infra.kafka.cliente;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.cliente.ClienteEvent;
import br.com.fiap.tiulanches.adapter.message.cliente.ClienteMessage;
import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;

@Service
public class EnviaCliente implements ClienteMessage {

    private final KafkaTemplate<String, Object> kafka;

    public EnviaCliente(KafkaTemplate<String, Object> kafka) {
		    this.kafka = kafka;
	  }

    @Override
    public void enviaMensagem(EventoEnum evento, ClienteDto cliente) {
        ClienteEvent clienteEvent = new ClienteEvent(evento, cliente);
        
        kafka.send("topico-cliente-pedido", clienteEvent);
    }    
}
