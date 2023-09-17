INSERT INTO produtos (categoria, 
                      nome,
					  descricao,
					  preco,
					  tempo_preparo,
					  imagem)
select 0 AS categoria,
       'X TUDÃO' AS nome,
       'Pão, Maionese, Catchup, 2 Hamburguer, 2 Presuntos, 2 Queijos, 2 Ovos, Bacon, Alface, Tomate, Milho e Batata' AS descricao,
       18.50 AS preco,
       30 AS tempo_preparo,
       'C:/Imagens/xtudao' AS imagem
UNION ALL       
select 1 AS categoria,
       'BATATA FRITA COMPLETA' AS nome,
       'Batata, Mussarela, Cheddar e Bacon' AS descricao,
       23.00 AS preco,
       15 AS tempo_preparo,
       'C:/Imagens/fritascompleta' AS imagem
UNION ALL       
select 2 AS categoria,
       'COCA COLA 2 LITROS' AS nome,
       'Coca Cola 2 Litros' AS descricao,
       10.00 AS preco,
       0 AS tempo_preparo,
       'C:/Imagens/cocacola2' AS imagem
UNION ALL       
select 3 AS categoria,
       'CASCATA KINDER' AS nome,
       'Taça com açaí, banana, morango, creme de leite ninho, Kinder Bueno, Kinder Ovo, calda de chocolate e calda de chocolate branco.' AS descricao,
       49.90 AS preco,
       30 AS tempo_preparo,
       'C:/Imagens/cascatakinder' AS imagem       					  