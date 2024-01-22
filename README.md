# TIU LANCHES
| :placard: Vitrine.Dev |     |
| -------------  | --- |
| :sparkles: Nome        | **Tiu Lanches**
| :label: Tecnologias | Java, Maven, Spring, MySQL, Docker, Kubernetes
| :rocket: URL         | 
| :fire: Desafio     | Tech Challenge FIAP

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) ![Static Badge](https://img.shields.io/badge/any_text-Version-blue?label=24.01.20)

<!-- Inserir imagem com a #vitrinedev ao final do link -->
![](https://www.notion.so/image/https%3A%2F%2Fimagens.jotaja.com%2Fempresa%2Ffcf91c6a-1626-4412-b5d0-845c777d5611.jpg?table=block&id=818bd35f-516d-459d-9525-f3bc2f7c2af6&spaceId=62941c71-5c2d-41d6-8c4f-a5f5b14de56c&width=2000&userId=06b981be-eaf4-4de6-9a12-a77aa351d285&cache=v2#vitrinedev)

# Detalhes do projeto
## Objetivo
Projeto criado para conclusão da Pós Gradução em Software Architecture pela FIAP

## Requisitos
Há uma lanchonete em expansão devido ao seu sucesso. Mas sem um sistema estruturado para atender as necessidades da lanchonete os atendimentos podem ser caóticos pela devido a alta demanda.

Quando o cliente chega ao estabelecimento ele precisa utilizar um sistema de autoatendimento na recepção, onde poderá criar seu pedido escolhendo o lanche, acompanhamento, bebida e sobremesa. Precisa possibilitar que o cliente possa realizar o pagamando com cartão de crédito, qrcode, pix ou conta do mercado pago.

Com o pedido criado é necessário exibir em um painel todo o processo que o pedido está passando em real time para que o solicitante acompanhe para poder retirar no balcão. Com o pedido pronto será exibido no painel a informação de sua conclusão.

O sistema precisa ser capaz de escalar de acordo com a demanda.

## Documentação
Para acessar a documentação com informações como o DDD deste projeto clicar no link para o [Notion](https://luisferrarezi.notion.site/Tiu-Lanches-818bd35f516d459d9525f3bc2f7c2af6) 

# Arquitetura
## Negócio
O modelo de negócio foi pensado como ponto de partida o pedido, onde o cliente pode se identificar ou não, caso se identifique será exigido que informe CPF, email e nome. 

Com o pedido criado será enviado uma criação de preferência para o mercado pago onde permitirá ao cliente escolher a forma de pagamento.

Quando o pagamento é efetivado o pedido é encaminhado para a produção(cozinha) e quando concluído o prepado é finalmente disponibilizado para entrega ao balcão que se encarrega de garantir que o pedido será entregue o cliente correto e finalizar o pedido.

Segue uma ilustração deste processo:
![](https://luisferrarezi.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F62941c71-5c2d-41d6-8c4f-a5f5b14de56c%2F0c7413b3-ced0-4a4d-8f8e-735989853f55%2FTiuLanchesMapaContexto.png?table=block&id=ea599cfc-189c-4b5a-b3eb-21db292154fe&spaceId=62941c71-5c2d-41d6-8c4f-a5f5b14de56c&width=2000&userId=&cache=v2)

## Infraestrutura
Para atender os requisitos da aplicação ser capaz de escalar foi implementado o Kubernetes onde são criados dois deployments um para a base de dados e outro para a aplicação.

A base de dados terá acesso a um volume no servidor de forma que os dados fiquem protegidos e não sejam perdidos no caso de seu POD precisar ser recriado.

Para a aplicação o seu deployment inicia criando 2 PODs iniciais este inclusive é a quantidade mínima que precisa estar disponível, podendo chegar a 4 PODs. Para isso foram implementadas a liberação de alguns acessos para que o HPA possa monitorar e realizar a escalabilidade quando o uso de processamento ultrapasse 50%.

Cada um dos deployments serão criados com seus devidos services e confimaps, mas existe um secret para ser usado por ambos devido senhas e token de acesso ao mercado pago.

O ponto de acesso com a web fica a cargo do service da aplicação.

Segue uma ilustração da arquitetura criada no kubernetes localmente:
![](https://luisferrarezi.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F62941c71-5c2d-41d6-8c4f-a5f5b14de56c%2F6de5cee4-8046-493e-ab66-f612217780ec%2FTiuLanchesArquitatura.png?table=block&id=40932ea3-d7fe-40d1-a630-be3657d4cdbd&spaceId=62941c71-5c2d-41d6-8c4f-a5f5b14de56c&width=1940&userId=&cache=v2)

## Software
### Pattern
- Clean Architecture

### Linguagem
- Java - JDK 21

### Banco de dados
- MySql - 8.0

### Frameworks utilizados 
- Mercado Pago SDK - 2.1.17
- Spring Framework - 3.2.0
- Lombok
- Flyway
- Maven 
- Jackson Databind
- Log4j
- Spring Doc

### Variáveis de Ambiente
Existem variáveis de ambiente na aplicação que estão cadastradas no docker compose e para kubernetes para serem apenas executadas.

Para poder funcionar em IDE é necessário cadastrar as seguintes variáveis de ambiente:
- MYSQL_ROOT_PASSWORD=<SENHA_ROOT>
- DATASOURCE_URL=<URL_CONEXAO_BD>
- DATASOURCE_USERNAME=<USER_NAME_DB>
- DATASOURCE_PASSWORD=<SENHA_DB>
- ACCESS_TOKEN_MP=<TOKEN_MERCADO_PAGO>

# Execução da aplicação localmente
## Subir Aplicação via Docker Compose
- Baixe o docker compose do projeto em https://github.com/luisferrarezi/tiulanches/blob/main/compose/docker-compose.yml

~~~Execute
docker compose -f docker-compose.yml up --build -d
~~~

### Acessos 
- Aplicação: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui/index.html

## Subir Aplicação via K8s
- Baixe baixe todos os arquivos localizados em https://github.com/luisferrarezi/tiulanches/tree/main/kubernetes/local

~~~Execute
sh startapp.sh
~~~

Neste comando serão executados os arquivos yaml automaticamente, na sequência correta, entre subir a base de dados e subir a aplicação existe uma espera de 60 segundos para garantir que a base já estará funcional quando a aplicação subir. Somente após apresentar a mensagem de Projeto pronto que ele estará efetivamente ok para enviar requisições. Para isso é necessário ter o curl instalado no ambiente de execução do comando.

Caso não tenha ambiente para executar o bash por ser executado os comandos de acordo com a ordem disponibilizada abaixo 

~~~Execute
kubectl apply -f secrets.yaml
kubectl apply -f metrics.yaml
kubectl apply -f db-deployment.yaml
kubectl apply -f app-deployment.yaml
~~~

**OBS: É INDISPENSÁVEL QUE TODO O AMBIENTE ESTEJA FUNCIONAL ANTES DE REALIZAR QUALQUER TESTE**

### Acessos 
- Aplicação: http://localhost:31200
- Swagger: http://localhost:31200/swagger-ui/index.html

## Postman
Segue o link direto para o local onde a collection do postman está disponível: https://github.com/luisferrarezi/tiulanches/tree/main/Postman

# Vídeo explicativo
## Fase 2
Segue link do vídeo que explica como a arquitetura e aplicação estão estruturadas e como funciona o aplicativo desde a criação do pedido até a sua entrega.

https://youtu.be/kGFOxVzJDgw
