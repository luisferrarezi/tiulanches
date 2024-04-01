CREATE TABLE tiulanches.clientes(
	cpf varchar(11) NOT NULL,
	nome varchar(60) NOT NULL,
	email varchar(60) NOT NULL,
	logado tinyint NOT NULL DEFAULT 0,
	PRIMARY KEY (cpf)
);