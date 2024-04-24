package br.com.fiap.tiulanches.utils.cliente;

public enum ClienteEnum {
    CPF("68330488004"),
    NOME("teste"),
    EMAIL("teste@teste.com");

    private String valor;

    ClienteEnum(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return valor;
    }
}
