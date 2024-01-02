CREATE TABLE pagamentos(
	id_pagamento bigint(20) NOT NULL AUTO_INCREMENT,
	pago tinyint NOT NULL DEFAULT 0,
	id_mercado_pago varchar(400),
	ticket_url varchar(400),
	PRIMARY KEY (id_pagamento)
);
