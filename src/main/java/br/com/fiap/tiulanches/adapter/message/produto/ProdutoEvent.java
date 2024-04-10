package br.com.fiap.tiulanches.adapter.message.produto;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEvent {
    private EventoEnum evento;
    private ProdutoDto produtoDto;
}
