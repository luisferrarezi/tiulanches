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
import br.com.fiap.tiulanches.adapter.controller.PreferenciaExternoController;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.adapter.repository.pedido.ItemPedidoDto;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.core.exception.BusinessException;

@Service
public class PreferenciaMP implements PreferenciaExternoController{
	private final PagamentoController controller;
	
	public PreferenciaMP(PagamentoController controller){
		this.controller = controller;
	};
	
	public void criar(PedidoDto dto) {
		try {
  	   		MercadoPagoConfig.setAccessToken(System.getenv("ACCESS_TOKEN_MP"));  	   		
  	   		
  	   		PreferenceClient client = new PreferenceClient();
  	   		
	    	PreferenceRequest request = PreferenceRequest
	    								.builder()
	    								.externalReference(String.valueOf(dto.pagamento().idPagamento()))
	    								.items(informaItems(dto))	    
	    								.payer(informaPagador(dto))
	    								.paymentMethods(informaTiposPagamento())
	    								.build();	    	
	    	
	    	Preference preference = client.create(request);	    	
	    	
	    	controller.registra(new PagamentoDto(dto.pagamento().idPagamento(), Pago.NAO, null, preference.getSandboxInitPoint()));	    	
	    } catch (MPApiException e) {
	    	StringBuilder erro = new StringBuilder();
	    	erro.append("Falha integração Mercado Pago: ");
	    	erro.append(e.getMessage());
	    	
	    	throw new BusinessException(e.getMessage(), HttpStatus.BAD_REQUEST, new String(erro));
	    } catch (MPException e) {
	    	StringBuilder erro = new StringBuilder();
	    	erro.append("Falha integração Mercado Pago: ");
	    	erro.append(e.getMessage());
	    	
	    	throw new BusinessException(e.getMessage(), HttpStatus.BAD_REQUEST, new String(erro));
	    }	    
	}
	
	private List<PreferenceItemRequest> informaItems(PedidoDto pedido){
		List<PreferenceItemRequest> items = new ArrayList<>();		
	    for(ItemPedidoDto itemPedido : pedido.listItemPedido()) {
	    	
			PreferenceItemRequest item = PreferenceItemRequest.builder()
										.id(String.valueOf(itemPedido.idItem()))
					       				.title(itemPedido.produto().nome())
					       				.description(itemPedido.produto().descricao())
					       				.pictureUrl(itemPedido.produto().imagem())
					       				.quantity(itemPedido.quantidade())
					       				.currencyId("BRL")
					       				.unitPrice(itemPedido.precoUnitario())
					       				.build();

	    	items.add(item);	
	    }
	    
	    return items;
	}
	
	private PreferencePayerRequest informaPagador(PedidoDto pedido){		
	    if (pedido.cliente() != null) {
	    	return PreferencePayerRequest.builder()
	    			.email(pedido.cliente().email())
	    			.name(pedido.cliente().nome())
	    			.identification(IdentificationRequest.builder()
	    							.type("CPF")
	    							.number(pedido.cliente().cpf())
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
