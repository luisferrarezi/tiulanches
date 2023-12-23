package br.com.fiap.tiulanches.core.entitie.pedido;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import br.com.fiap.tiulanches.core.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.core.entitie.cliente.Cliente;
import br.com.fiap.tiulanches.core.entitie.pagamento.Pagamento;
import br.com.fiap.tiulanches.core.enums.Pago;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Pedido")
@Table(name = "PEDIDOS")
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
	@Id
	@Getter	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Código do pedido após ser criado", example = "17", required = true)	
	private long idPedido;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name="cpf")
	@Schema(description = "Cliente caso ele queira se identificar")	
	private Cliente cliente;
	
	@Getter
	@Setter
	@JsonManagedReference
	@OneToOne(mappedBy="pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	@Schema(description = "Pagamento do pedido")	
	private Pagamento pagamento;
	
	@Getter	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Schema(implementation = StatusPedido.class, description = "Status do pedido", example = "RECEBIDO", required = true)	
	private StatusPedido status;		
    
	@Getter
	@Setter
	@JsonManagedReference
	@OneToMany(mappedBy="pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	@Schema(description = "Lista itens do pedido", required = true)	
	private List<ItemPedido> listItemPedido = new ArrayList<>();
	
	public void adicionarItem(ItemPedido item) {
		this.listItemPedido.add(item);
		item.setPedido(this);	
	}
	
	public void removerItem(ItemPedido item) {
		this.listItemPedido.remove(item);			
	}	
	
	public void cadastrar(PedidoDto pedido) {
		this.cliente = pedido.cliente();		
		this.status = StatusPedido.RECEBIDO;
		
		Pagamento pagamento = new Pagamento();		
		this.pagamento = pagamento.criar(this);		
	}
	
	public void cancelar() {
		this.status = StatusPedido.CANCELADO;
	}	
	
	public void preparar() {
		this.status = StatusPedido.PREPARACAO;
	}		
	
	public void entregar() {
		this.status = StatusPedido.PRONTO;
	}		
	
	public void finalizar() {
		this.status = StatusPedido.FINALIZADO;
	}		
	
	public boolean isPermiteCancelar() {
		return this.status == StatusPedido.RECEBIDO && !isPago();
	}			
	
	public boolean isPermitePreparo() {
		return this.status == StatusPedido.RECEBIDO && isPago();
	}			
	
	public boolean isPermiteEntregar() {
		return this.status == StatusPedido.PREPARACAO && isPago();
	}			
	
	public boolean isPermiteFinalizar() {
		return this.status == StatusPedido.PRONTO && isPago();
	}				
	
	public boolean isPago() {
		return this.pagamento.getPago() == Pago.SIM;
	}
}
