package br.com.fiap.tiulanches.infra.kafka.produto;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.produto.ProdutoEvent;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.enums.Categoria;

class EnviaProdutoTest {

    private EnviaProduto enviaProduto;
    private ProdutoDto produtoDto;

    private static final long ID_PRODUTO = 10L;
    private static final String NOME = "Teste";
    private static final String DESCRICAO = "Teste";
    private static final BigDecimal PRECO = BigDecimal.valueOf(11.20);
    private static final int TEMPO_PREPARO = 10;
    private static final String IMAGEM = "Teste";

    @Mock
    KafkaTemplate<String, Object> kafka;

    @BeforeEach
    @SuppressWarnings("unchecked")    
    void beforeEach(){
        kafka = Mockito.mock(KafkaTemplate.class);
        enviaProduto = new EnviaProduto(kafka);
    }

    @Test
    void constructorEnviaProdutoTest(){
        enviaProduto = new EnviaProduto(kafka);

        assertNotEquals(null, enviaProduto);
    }

    @Test
    void enviaMensagemTest(){
        produtoDto = new ProdutoDto(ID_PRODUTO, Categoria.ACOMPANHAMENTO, NOME, DESCRICAO, PRECO, TEMPO_PREPARO, IMAGEM);

        when(kafka.send(anyString(), any(ProdutoEvent.class))).thenReturn(null);
        assertDoesNotThrow(()->enviaProduto.enviaMensagem(EventoEnum.CREATE, produtoDto));
    }
}
