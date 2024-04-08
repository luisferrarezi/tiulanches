package br.com.fiap.tiulanches.adapter.mensagem.produto;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEvent {
    private String evento;
    private ProdutoDto produtoDto;
}
