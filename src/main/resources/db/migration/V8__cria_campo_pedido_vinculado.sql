alter table tiulanches.clientes add column pedido_vinculado tinyint DEFAULT 0;

update tiulanches.clientes set pedido_vinculado = 0; 

alter table tiulanches.clientes modify column pedido_vinculado tinyint not null DEFAULT 0;

alter table tiulanches.produtos add column pedido_vinculado tinyint DEFAULT 0;

update tiulanches.produtos set pedido_vinculado = 0; 

alter table tiulanches.produtos modify column pedido_vinculado tinyint not null DEFAULT 0;