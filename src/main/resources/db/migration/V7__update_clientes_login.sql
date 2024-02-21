ALTER TABLE clientes
ADD logado tinyint NOT NULL DEFAULT 0;

UPDATE clientes SET logado = 0;