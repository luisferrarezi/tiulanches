package br.com.fiap.tiulanches.core.domain.dto;

import java.math.BigDecimal;
import br.com.fiap.tiulanches.core.domain.enums.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {
	private long idProduto;
	private Categoria categoria;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private int tempoPreparo;
	private String imagem;
}
