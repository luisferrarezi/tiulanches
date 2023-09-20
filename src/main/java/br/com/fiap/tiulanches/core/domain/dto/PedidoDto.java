package br.com.fiap.tiulanches.core.domain.dto;


import br.com.fiap.tiulanches.core.domain.enums.Pago;
import br.com.fiap.tiulanches.core.domain.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDto {
	private long idPedido;
	private String cpf;
	private StatusPedido status;
	private String qrcode;
	private Pago pago;
}
