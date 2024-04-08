package br.com.fiap.tiulanches.infra.kafka.produto;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.mensagem.produto.ProdutoMensagem;
import br.com.fiap.tiulanches.adapter.mensagem.produto.ProdutoEvent;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;

@Service
public class EnviaMensagem implements ProdutoMensagem {

    private final KafkaTemplate<String, Object> kafka;

    public EnviaMensagem(KafkaTemplate<String, Object> kafka) {
		    this.kafka = kafka;
	  }

    @Override
    public void enviaMensagem(String evento, ProdutoDto produto) {
        ProdutoEvent produtoEvent = new ProdutoEvent(evento, produto);
        
        kafka.send("topico-produto-pedido", produtoEvent);        
    }    
}
