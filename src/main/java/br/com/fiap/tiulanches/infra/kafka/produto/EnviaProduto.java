package br.com.fiap.tiulanches.infra.kafka.produto;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.produto.ProdutoEvent;
import br.com.fiap.tiulanches.adapter.message.produto.ProdutoMessage;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;

@Service
public class EnviaProduto implements ProdutoMessage {

    private final KafkaTemplate<String, Object> kafka;

    public EnviaProduto(KafkaTemplate<String, Object> kafka) {
	    this.kafka = kafka;
    }

    @Override
    public void enviaMensagem(EventoEnum evento, ProdutoDto produto) {
        ProdutoEvent produtoEvent = new ProdutoEvent(evento, produto);
        
        kafka.send("topico-produto-pedido", produtoEvent);        
    }    
}
