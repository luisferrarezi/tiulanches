package br.com.fiap.tiulanches.core.domain.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import br.com.fiap.tiulanches.core.domain.enums.StatusPedido;
import br.com.fiap.tiulanches.core.domain.dto.PedidoDto;
import br.com.fiap.tiulanches.core.domain.enums.Pago;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Pedido")
@Table(name = "PEDIDOS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedido;
	
	@ManyToOne
	@JoinColumn(name="cpf")
	private Cliente cliente;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private StatusPedido status;
	
	@NotBlank
	@Size(max=400)
	private String qrcode;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Pago pago;	
    
	@JsonManagedReference
	@OneToMany(mappedBy="pedido", cascade = CascadeType.ALL, orphanRemoval = true)	
	private List<ItemPedido> listItemPedido = new ArrayList<>();
	
	public void adicionarItem(ItemPedido item) {
		this.listItemPedido.add(item);
		item.setPedido(this);	
	}
	
	public void removerItem(ItemPedido item) {
		this.listItemPedido.remove(item);			
	}	
	
	public void criar(PedidoDto pedido) {
		this.cliente = pedido.cliente();
		this.setPago(Pago.SIM);
		this.setStatus(StatusPedido.RECEBIDO);
		this.setQrcode("qrcode123456");
	}
	
	public void atualizar(PedidoDto pedido) {
		if (pedido.cliente() != null) {
			this.cliente = pedido.cliente();
		}
	}	
}
