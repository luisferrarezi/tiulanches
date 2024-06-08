CREATE TABLE tiulanches.clientes(
	cpf varchar(11) NOT NULL,
	nome varchar(60) NOT NULL,
	email varchar(60) NOT NULL,
	endereco varchar(100) NOT NULL,
	numero varchar(10) NOT NULL,
	bairro varchar(100) NOT NULL,
	cidade varchar(100) NOT NULL,
	estado varchar(2) NOT NULL,
	cep varchar(9) NOT NULL,
	telefone varchar(15) NOT NULL,
	PRIMARY KEY (cpf)		
);