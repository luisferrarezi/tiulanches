package br.com.fiap.tiulanches.core.entitie.pagamento;

import br.com.fiap.tiulanches.core.enums.Pago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "ConsultaPagamento")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaPagamento {	
	
	@Id	
	@Schema(description = "Pedido que o pagamento pertence", required = true)
	private long idPedido;
	
	@Enumerated(EnumType.ORDINAL)
	@Schema(implementation = Pago.class, description = "Pago (SIM,NAO)", example = "SIM", required = true)	
	private Pago pago;
}
