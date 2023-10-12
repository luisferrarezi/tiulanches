package br.com.fiap.tiulanches.adapter.infra.swagger;

public final class PedidoResponseSwagger {
	public static final String POST = "{\r\n"
       		+ "  \"cliente\": {\r\n"
       		+ "    \"cpf\": \"04162311013\"\r\n"
       		+ "  },\r\n"
       		+ "  \"listItemPedido\":[\r\n"
       		+ "    {\r\n"
       		+ "      \"produto\": {\r\n"
       		+ "        \"idProduto\": 1\r\n"
       		+ "      },\r\n"
       		+ "      \"quantidade\": 3,\r\n"
       		+ "      \"observacao\": \"Sem queijo e mostarda\"\r\n"
       		+ "    },\r\n"
       		+ "    {\r\n"
       		+ "      \"produto\": {\r\n"
       		+ "        \"idProduto\": 2\r\n"
       		+ "      },\r\n"
       		+ "      \"quantidade\": 1\r\n"
       		+ "    }\r\n"
       		+ "  ]\r\n"
       		+ "}";
	
	public static final String CREATED = "{\r\n"
			+ "    \"idPedido\": 17,\r\n"
			+ "    \"cliente\": {\r\n"
			+ "        \"cpf\": \"04162311013\",\r\n"
			+ "        \"nome\": \"Luiss\",\r\n"
			+ "        \"email\": \"teste@gmail.com\"\r\n"
			+ "    },\r\n"
			+ "    \"status\": \"RECEBIDO\",\r\n"
			+ "    \"qrcode\": \"qrcode123456\",\r\n"
			+ "    \"pago\": \"SIM\",\r\n"
			+ "    \"listItemPedido\": [\r\n"
			+ "        {\r\n"
			+ "            \"idItem\": 31,\r\n"
			+ "            \"produto\": {\r\n"
			+ "                \"idProduto\": 1,\r\n"
			+ "                \"categoria\": \"LANCHE\",\r\n"
			+ "                \"nome\": \"X TUDÃO\",\r\n"
			+ "                \"descricao\": \"Pão, Maionese, Catchup, 2 Hamburguer, 2 Presuntos, 2 Queijos, 2 Ovos, Bacon, Alface, Tomate, Milho e Batata\",\r\n"
			+ "                \"preco\": 18.50,\r\n"
			+ "                \"tempoPreparo\": 30,\r\n"
			+ "                \"imagem\": \"C:/Imagens/xtudao\"\r\n"
			+ "            },\r\n"
			+ "            \"precoUnitario\": 18.50,\r\n"
			+ "            \"quantidade\": 3,\r\n"
			+ "            \"observacao\": \"Sem queijo e mostarda\"\r\n"
			+ "        },\r\n"
			+ "        {\r\n"
			+ "            \"idItem\": 32,\r\n"
			+ "            \"produto\": {\r\n"
			+ "                \"idProduto\": 2,\r\n"
			+ "                \"categoria\": \"ACOMPANHAMENTO\",\r\n"
			+ "                \"nome\": \"BATATA FRITA COMPLETA\",\r\n"
			+ "                \"descricao\": \"Batata, Mussarela, Cheddar e Bacon\",\r\n"
			+ "                \"preco\": 23.00,\r\n"
			+ "                \"tempoPreparo\": 15,\r\n"
			+ "                \"imagem\": \"C:/Imagens/fritascompleta\"\r\n"
			+ "            },\r\n"
			+ "            \"precoUnitario\": 23.00,\r\n"
			+ "            \"quantidade\": 1,\r\n"
			+ "            \"observacao\": null\r\n"
			+ "        }\r\n"
			+ "    ]\r\n"
			+ "}";		
	
	public static final String GETBYID = "{\r\n"
			+ "    \"idPedido\": 17,\r\n"
			+ "    \"cliente\": {\r\n"
			+ "        \"cpf\": \"04162311013\",\r\n"
			+ "        \"nome\": \"Luiss\",\r\n"
			+ "        \"email\": \"teste@gmail.com\"\r\n"
			+ "    },\r\n"
			+ "    \"status\": \"RECEBIDO\",\r\n"
			+ "    \"qrcode\": \"qrcode123456\",\r\n"
			+ "    \"pago\": \"SIM\",\r\n"
			+ "    \"listItemPedido\": [\r\n"
			+ "        {\r\n"
			+ "            \"idItem\": 31,\r\n"
			+ "            \"produto\": {\r\n"
			+ "                \"idProduto\": 1,\r\n"
			+ "                \"categoria\": \"LANCHE\",\r\n"
			+ "                \"nome\": \"X TUDÃO\",\r\n"
			+ "                \"descricao\": \"Pão, Maionese, Catchup, 2 Hamburguer, 2 Presuntos, 2 Queijos, 2 Ovos, Bacon, Alface, Tomate, Milho e Batata\",\r\n"
			+ "                \"preco\": 18.50,\r\n"
			+ "                \"tempoPreparo\": 30,\r\n"
			+ "                \"imagem\": \"C:/Imagens/xtudao\"\r\n"
			+ "            },\r\n"
			+ "            \"precoUnitario\": 18.50,\r\n"
			+ "            \"quantidade\": 3,\r\n"
			+ "            \"observacao\": \"Sem queijo e mostarda\"\r\n"
			+ "        },\r\n"
			+ "        {\r\n"
			+ "            \"idItem\": 32,\r\n"
			+ "            \"produto\": {\r\n"
			+ "                \"idProduto\": 2,\r\n"
			+ "                \"categoria\": \"ACOMPANHAMENTO\",\r\n"
			+ "                \"nome\": \"BATATA FRITA COMPLETA\",\r\n"
			+ "                \"descricao\": \"Batata, Mussarela, Cheddar e Bacon\",\r\n"
			+ "                \"preco\": 23.00,\r\n"
			+ "                \"tempoPreparo\": 15,\r\n"
			+ "                \"imagem\": \"C:/Imagens/fritascompleta\"\r\n"
			+ "            },\r\n"
			+ "            \"precoUnitario\": 23.00,\r\n"
			+ "            \"quantidade\": 1,\r\n"
			+ "            \"observacao\": null\r\n"
			+ "        }\r\n"
			+ "    ]\r\n"
			+ "}";	
	
	public static final String GETBYPAGE = "{\r\n"
			+ "    \"content\": [\r\n"
			+ "        {\r\n"
			+ "            \"idPedido\": 1,\r\n"
			+ "            \"cliente\": {\r\n"
			+ "                \"cpf\": \"23456789412\",\r\n"
			+ "                \"nome\": \"Teste\",\r\n"
			+ "                \"email\": \"teste@gmail.com\"\r\n"
			+ "            },\r\n"
			+ "            \"status\": \"RECEBIDO\",\r\n"
			+ "            \"qrcode\": \"qrcode123456\",\r\n"
			+ "            \"pago\": \"SIM\",\r\n"
			+ "            \"listItemPedido\": [\r\n"
			+ "                {\r\n"
			+ "                    \"idItem\": 1,\r\n"
			+ "                    \"produto\": {\r\n"
			+ "                        \"idProduto\": 1,\r\n"
			+ "                        \"categoria\": \"LANCHE\",\r\n"
			+ "                        \"nome\": \"X TUDÃO\",\r\n"
			+ "                        \"descricao\": \"Pão, Maionese, Catchup, 2 Hamburguer, 2 Presuntos, 2 Queijos, 2 Ovos, Bacon, Alface, Tomate, Milho e Batata\",\r\n"
			+ "                        \"preco\": 18.50,\r\n"
			+ "                        \"tempoPreparo\": 30,\r\n"
			+ "                        \"imagem\": \"C:/Imagens/xtudao\"\r\n"
			+ "                    },\r\n"
			+ "                    \"precoUnitario\": 18.50,\r\n"
			+ "                    \"quantidade\": 3,\r\n"
			+ "                    \"observacao\": \"Luis\"\r\n"
			+ "                },\r\n"
			+ "                {\r\n"
			+ "                    \"idItem\": 2,\r\n"
			+ "                    \"produto\": {\r\n"
			+ "                        \"idProduto\": 2,\r\n"
			+ "                        \"categoria\": \"ACOMPANHAMENTO\",\r\n"
			+ "                        \"nome\": \"BATATA FRITA COMPLETA\",\r\n"
			+ "                        \"descricao\": \"Batata, Mussarela, Cheddar e Bacon\",\r\n"
			+ "                        \"preco\": 23.00,\r\n"
			+ "                        \"tempoPreparo\": 15,\r\n"
			+ "                        \"imagem\": \"C:/Imagens/fritascompleta\"\r\n"
			+ "                    },\r\n"
			+ "                    \"precoUnitario\": 23.00,\r\n"
			+ "                    \"quantidade\": 1,\r\n"
			+ "                    \"observacao\": \"hahahahah\"\r\n"
			+ "                }\r\n"
			+ "            ]\r\n"
			+ "        }\r\n"
			+ "    ],\r\n"
			+ "    \"pageable\": {\r\n"
			+ "        \"pageNumber\": 0,\r\n"
			+ "        \"pageSize\": 10,\r\n"
			+ "        \"sort\": {\r\n"
			+ "            \"empty\": true,\r\n"
			+ "            \"sorted\": false,\r\n"
			+ "            \"unsorted\": true\r\n"
			+ "        },\r\n"
			+ "        \"offset\": 0,\r\n"
			+ "        \"paged\": true,\r\n"
			+ "        \"unpaged\": false\r\n"
			+ "    },\r\n"
			+ "    \"last\": false,\r\n"
			+ "    \"totalPages\": 1,\r\n"
			+ "    \"totalElements\": 1,\r\n"
			+ "    \"size\": 1,\r\n"
			+ "    \"number\": 0,\r\n"
			+ "    \"sort\": {\r\n"
			+ "        \"empty\": true,\r\n"
			+ "        \"sorted\": false,\r\n"
			+ "        \"unsorted\": true\r\n"
			+ "    },\r\n"
			+ "    \"first\": true,\r\n"
			+ "    \"numberOfElements\": 1,\r\n"
			+ "    \"empty\": false\r\n"
			+ "}";	
	
	public static final String BADREQUEST = "[\r\n"
			+ "    {\r\n"
			+ "        \"campo\": \"quantidade\",\r\n"
			+ "        \"mensagem\": \"Quantidade mínima é 1\"\r\n"
			+ "    }\r\n"
			+ "]";
}
