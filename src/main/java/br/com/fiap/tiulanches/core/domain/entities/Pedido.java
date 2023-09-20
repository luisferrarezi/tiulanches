package br.com.fiap.tiulanches.core.domain.entities;

import br.com.fiap.tiulanches.core.domain.enums.StatusPedido;
import br.com.fiap.tiulanches.core.domain.enums.Pago;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PEDIDOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedido;
	
	@Size(max=11)
	private String cpf;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private StatusPedido status;
	
	@NotBlank
	@Size(max=400)
	private String qrcode;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Pago pago;
}
