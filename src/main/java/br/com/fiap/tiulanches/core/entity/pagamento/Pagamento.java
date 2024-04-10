package br.com.fiap.tiulanches.core.entity.pagamento;

import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.core.enums.Pago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@Enumerated(EnumType.ORDINAL)
	@Schema(implementation = Pago.class, description = "Pago (SIM,NAO)", example = "SIM", required = true)	
	private Pago pago;
	
	@Size(max=400)
	@Schema(description = "Codigo do pedido no mercado pago", example = "1320363299")
	private String idMercadoPago;	
	
	@Size(max=400)
	@Schema(description = "URL para cliente realizar pagamento", example = "https://www.mercadopago.com.br/payments/123456789/ticket?caller_id=123456&hash=123e4567-e89b-12d3-a456-426655440000", maxLength = 400)
	private String ticketUrl;
	
	public Pagamento criar() {
		this.pago = Pago.NAO;
		
		return this;
	};
	
	public void registrar(PagamentoDto pagamento) {
		this.idPagamento = pagamento.idPagamento();
		validaIdMercadoPago(pagamento.idMercadoPago());
		validaTicketUrl(pagamento.ticketUrl());
		validaPago(pagamento.pago());
	};
	
	private void validaIdMercadoPago(String idMercadoPago) {
		if (idMercadoPago != null) {
			this.idMercadoPago = idMercadoPago;
		}				
	}
	
	private void validaTicketUrl(String ticketUrl) {
		if (ticketUrl != null) {
			this.ticketUrl = ticketUrl;
		}				
	}
	
	private void validaPago(Pago pago) {
		if (pago != null) {
			this.pago = pago;
		}				
	}	
}
