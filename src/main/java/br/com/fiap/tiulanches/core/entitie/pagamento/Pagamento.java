package br.com.fiap.tiulanches.core.entitie.pagamento;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.core.entitie.pedido.Pedido;
import br.com.fiap.tiulanches.core.enums.Pago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Pagamento")
@Table(name = "PAGAMENTOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {		
	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Código de pagamento do pedido interno", example = "7", required = true)
	private long idPagamento;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name="id_pedido", nullable = false)
	@Schema(description = "Pedido que o pagamento pertence", required = true)	
	private Pedido pedido;
	
	@Enumerated(EnumType.ORDINAL)
	@Schema(implementation = Pago.class, description = "Pago (SIM,NAO)", example = "SIM", required = true)	
	private Pago pago;
	
	@Size(max=400)
	@Schema(description = "Codigo do pedido no mercado pago", example = "1")
	private String idMercadoPago;	
	
	@Size(max=400)
	@Schema(description = "URL para cliente realizar pagamento", example = "https://www.mercadopago.com.br/payments/123456789/ticket?caller_id=123456&hash=123e4567-e89b-12d3-a456-426655440000", maxLength = 400)
	private String ticketUrl;
	
	public Pagamento criar(Pedido pedido) {
		this.pago = Pago.NAO;
		this.pedido = pedido;
		
		return this;
	};
	
	public void alterar(PagamentoDto pagamento) {
		this.idPagamento = pagamento.idPagamento();
		this.idMercadoPago = pagamento.idMercadoPago();
		this.ticketUrl = pagamento.ticketUrl();
		this.pedido = pagamento.pedido();
		this.pago = pagamento.pago();
	};	
}
