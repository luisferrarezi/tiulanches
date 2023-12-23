CREATE TABLE pedidos(
	id_pedido bigint(20) NOT NULL AUTO_INCREMENT,
	cpf varchar(11) NOT NULL,
	status tinyint NOT NULL DEFAULT 0,	
	PRIMARY KEY (id_pedido)
);

ALTER TABLE pedidos ADD CONSTRAINT fk_pedido_cliente FOREIGN KEY (cpf) REFERENCES clientes (cpf);
