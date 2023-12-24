package br.com.fiap.tiulanches.infra.mercadopago;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodsRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

import br.com.fiap.tiulanches.adapter.controller.PagamentoController;
import br.com.fiap.tiulanches.adapter.controller.PagamentoExternoController;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.core.entitie.pedido.ItemPedido;
import br.com.fiap.tiulanches.core.exception.BusinessException;

@Service
public class MPpagamento implements PagamentoExternoController{
	private final PagamentoController controller;
	
	public MPpagamento(PagamentoController controller){
		this.controller = controller;
	};
	
	public void criar(PedidoDto pedido) {
		try {
  	   		MercadoPagoConfig.setAccessToken(System.getenv("ACCESS_TOKEN_MP"));  	   		
  	   		
  	   		PreferenceClient client = new PreferenceClient();
  	   		
	    	PreferenceRequest request = PreferenceRequest
	    								.builder()
	    								.items(informaItems(pedido))	    
	    								.payer(informaPagador(pedido))
	    								.paymentMethods(informaTiposPagamento())
	    								.build();	    	
	    	
	    	Preference preference = client.create(request);
	    	
	    	controller.altera(new PagamentoDto(pedido.pagamento().getIdPagamento(), pedido.pagamento().getPedido(), pedido.pagamento().getPago(), preference.getId(), preference.getSandboxInitPoint()));	    	
	    } catch (MPApiException e) {
	    	throw new BusinessException(e.getMessage(), HttpStatus.BAD_REQUEST, new String("Mercado Pago"));
	    } catch (MPException e) {
	    	throw new BusinessException(e.getMessage(), HttpStatus.BAD_REQUEST, new String("Mercado Pago"));
	    }	    
	}
	
	private List<PreferenceItemRequest> informaItems(PedidoDto pedido){
		List<PreferenceItemRequest> items = new ArrayList<>();		
	    for(ItemPedido itemPedido : pedido.listItemPedido()) {
	    	
			PreferenceItemRequest item = PreferenceItemRequest.builder()
					       				.title(itemPedido.getProduto().getNome())
					       				.description(itemPedido.getProduto().getDescricao())
					       				.pictureUrl(itemPedido.getProduto().getImagem())
					       				.quantity(itemPedido.getQuantidade())
					       				.currencyId("BRL")
					       				.unitPrice(itemPedido.getPrecoUnitario())
					       				.build();

	    	items.add(item);	
	    }
	    
	    return items;
	}
	
	private PreferencePayerRequest informaPagador(PedidoDto pedido){		
	    if (pedido.cliente() != null) {
	    	return PreferencePayerRequest.builder()
	    			.email(pedido.cliente().getEmail())
	    			.name(pedido.cliente().getNome())
	    			.identification(IdentificationRequest.builder()
	    							.type("CPF")
	    							.number(pedido.cliente().getCpf())
	    							.build())
	    			.build();
	    }		
	    
	    return null;
	}	
	
	private PreferencePaymentMethodsRequest informaTiposPagamento(){	    
	    List<PreferencePaymentMethodRequest> listExcludedPaymentMethodRequests = new ArrayList<>();
	    listExcludedPaymentMethodRequests.add(PreferencePaymentMethodRequest.builder().id("bolbradesco").build());
	    listExcludedPaymentMethodRequests.add(PreferencePaymentMethodRequest.builder().id("pec").build());
	    												
	    return PreferencePaymentMethodsRequest
	    		.builder()
				.excludedPaymentMethods(listExcludedPaymentMethodRequests)
				.installments(1)
				.build(); 
	}		
}
