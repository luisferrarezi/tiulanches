package br.com.fiap.tiulanches.adapter.mensagem.produto;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;

public interface ProdutoMensagem {
    public void enviaMensagem(String evento, ProdutoDto produto);
}
    	