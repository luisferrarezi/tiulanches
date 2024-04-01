CREATE TABLE tiulanches.pedidos(
	id_pedido bigint(20) NOT NULL AUTO_INCREMENT,
	id_pagamento bigint(20) NOT NULL,
	cpf varchar(11),
	status tinyint NOT NULL DEFAULT 0,	
	PRIMARY KEY (id_pedido)
);

ALTER TABLE pedidos ADD CONSTRAINT fk_pedido_cliente FOREIGN KEY (cpf) REFERENCES clientes (cpf);
ALTER TABLE pedidos ADD CONSTRAINT fk_pedido_pagamento FOREIGN KEY (id_pagamento) REFERENCES pagamentos (id_pagamento);
