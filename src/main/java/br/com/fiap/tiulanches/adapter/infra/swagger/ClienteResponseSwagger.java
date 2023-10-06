package br.com.fiap.tiulanches.adapter.infra.swagger;

public final class ClienteResponseSwagger {
	public static final String PUT = "{\r\n"
			+ "  \"nome\": \"Luis Junior\",\r\n"
			+ "  \"email\": \"luisjr@gmail.com\"\r\n"
			+ "}";		
	
	public static final String UPDATED = "{\r\n"
			+ "  \"cpf\": \"70636213005\",\r\n"			
			+ "  \"nome\": \"Luis Junior\",\r\n"
			+ "  \"email\": \"luisjr@gmail.com\"\r\n"
			+ "}";			
	
	public static final String CREATED = "{\r\n"
			+ "  \"cpf\": \"70636213005\",\r\n"
			+ "  \"nome\": \"Luis Antonio\",\r\n"
			+ "  \"email\": \"luisantonio@gmail.com\"\r\n"
			+ "}";		
	
	public static final String BADREQUEST = "{\r\n"
			+ "    \"campo\": \"CPF\",\r\n"
			+ "    \"mensagem\": \"CPF não informado!\"\r\n"
			+ "}";
}
