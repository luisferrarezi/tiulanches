package br.com.fiap.tiulanches.core.entity.painelpedido;

import br.com.fiap.tiulanches.core.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "PainelPedido")
@AllArgsConstructor
@NoArgsConstructor
public class PainelPedido {
	
	@Id
	@Getter	
	@Schema(description = "Código do pedido após ser criado", example = "17", required = true)	
	private long idPedido;
	
	@Getter	
	@Enumerated(EnumType.ORDINAL)
	@Schema(implementation = StatusPedido.class, description = "Status do pedido", example = "RECEBIDO", required = true)	
	private StatusPedido status;
}
