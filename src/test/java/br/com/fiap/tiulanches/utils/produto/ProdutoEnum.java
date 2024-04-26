package br.com.fiap.tiulanches.utils.produto;

import java.math.BigDecimal;

public enum ProdutoEnum {    
    ID_PRODUTO(10L),
    NOME("Teste"),
    DESCRICAO("Teste"),
    PRECO(BigDecimal.valueOf(11.20)),
    TEMPO_PREPARO(10),
    IMAGEM("Teste");

    private Object valor;

    ProdutoEnum(Object valor){
        this.valor = valor;
    }

    public Object getValor(){
        return valor;
    }
}
