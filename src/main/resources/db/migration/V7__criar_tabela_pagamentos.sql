CREATE TABLE pagamentos(
	id_pagamento bigint(20) NOT NULL AUTO_INCREMENT,	
	id_pedido bigint(20) NOT NULL,
	pago tinyint NOT NULL DEFAULT 0,
	id_mercado_pago bigint(20),
	ticket_url varchar(400),
	PRIMARY KEY (id_pagamento)
);

ALTER TABLE pagamentos ADD CONSTRAINT fk_pagamento_pedido FOREIGN KEY (id_pedido) REFERENCES pedidos (id_pedido);