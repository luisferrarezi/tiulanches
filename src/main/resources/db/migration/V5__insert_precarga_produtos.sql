INSERT INTO PRODUTOS (CATEGORIA, 
                      NOME,
					  DESCRICAO,
					  PRECO,
					  TEMPO_PREPARO,
					  IMAGEM)
select 0 AS CATEGORIA,
       'X TUDÃO' AS NOME,
       'Pão, Maionese, Catchup, 2 Hamburguer, 2 Presuntos, 2 Queijos, 2 Ovos, Bacon, Alface, Tomate, Milho e Batata' AS DESCRICAO,
       18.50 AS PRECO,
       30 AS TEMPO_PREPARO,
       'C:/Imagens/xtudao' AS IMAGEM
UNION ALL       
select 1 AS CATEGORIA,
       'BATATA FRITA COMPLETA' AS NOME,
       'Batata, Mussarela, Cheddar e Bacon' AS DESCRICAO,
       23.00 AS PRECO,
       15 AS TEMPO_PREPARO,
       'C:/Imagens/fritascompleta' AS IMAGEM
UNION ALL       
select 2 AS CATEGORIA,
       'COCA COLA 2 LITROS' AS NOME,
       'Coca Cola 2 Litros' AS DESCRICAO,
       10.00 AS PRECO,
       0 AS TEMPO_PREPARO,
       'C:/Imagens/cocacola2' AS IMAGEM
UNION ALL       
select 3 AS CATEGORIA,
       'CASCATA KINDER' AS NOME,
       'Taça com açaí, banana, morango, creme de leite ninho, Kinder Bueno, Kinder Ovo, calda de chocolate e calda de chocolate branco.' AS DESCRICAO,
       49.90 AS PRECO,
       30 AS TEMPO_PREPARO,
       'C:/Imagens/cascatakinder' AS IMAGEM       					  