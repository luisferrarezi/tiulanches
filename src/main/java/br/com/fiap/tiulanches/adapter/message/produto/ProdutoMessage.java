package br.com.fiap.tiulanches.adapter.message.produto;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;

public interface ProdutoMessage {
    public void enviaMensagem(EventoEnum evento, ProdutoDto produto);
}
    	