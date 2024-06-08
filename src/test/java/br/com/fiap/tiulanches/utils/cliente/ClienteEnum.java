package br.com.fiap.tiulanches.utils.cliente;

public enum ClienteEnum {
    CPF("68330488004"),
    NOME("teste"),
    EMAIL("teste@teste.com"),
    ENDERECO("Qualquer"),
    NUMERO("1234"),
    BAIRRO("Qualquer um"),
    CIDADE("Sei lá"),
    ESTADO("SP"),
    CEP("11111-000"),
    TELEFONE("(11) 99999-9999");

    private String valor;

    ClienteEnum(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return valor;
    }
}
