CREATE TABLE tiulanches.produtos(
	id_produto bigint(20) NOT NULL AUTO_INCREMENT,
	categoria tinyint NOT NULL,
	nome varchar(30) NOT NULL,
	descricao varchar(200) NOT NULL,
	preco decimal(4,2) NOT NULL,
	tempo_preparo tinyint DEFAULT 0,
	imagem varchar(400) DEFAULT NULL,
	PRIMARY KEY (id_produto)
);