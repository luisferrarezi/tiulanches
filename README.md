# TIU LANCHES
| :placard: Vitrine.Dev |     |
| -------------  | --- |
| :sparkles: Nome        | **Tiu Lanches**
| :label: Tecnologias | Java, Maven, Spring, MySQL, Docker, Kubernetes, Kafka
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
- Spring Framework
- Lombok
- Flyway
- Maven 
- Jackson Databind
- Log4j
- Spring Doc
- Kafka
- Jacoco
- JUnit
- Mockito

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

# Alterações no Projeto
## Novos recursos
Para a conclusão da 3ª Fase do projeto foi necessário criar novos recursos:
- Azure Function -> Mais detalhes acessar o [Readme](https://github.com/luisferrarezi/tiulanches-auth-function) do projeto.
- Terraform App -> Mais detalhes acessar o [Readme](https://github.com/luisferrarezi/tiulanches-app-terraform) do projeto.
- Terraform Database -> Mais detalhes acessar o [Readme](https://github.com/luisferrarezi/tiulanches-database-function) do projeto.

## Automação
Atualmente para esta branch existem 2 níveis de automação, explicado abaixo:

- Pull Request: este é executado no momento que é criado o PR, e nele é realizado um build para garantir que a aplicação não foi quebrada após alteração.
- Push: este é executado somente após o PR ter sido aprovado e executado o merge para a main, primeiro é realizado o build da aplicação, estando tudo ok  é então logado no Container Registry da Azure, criado a imagem como latest e faz o upload dela para o ACR. 
        Finalmente cria-se o ConfigMap com variáveis de ambiente necessárias para serem utilizadas pela aplicação no AKS, feito isso são utilizados os yamls configmap.yaml e app-deployment.yaml, para subir a aplicação no AKS.

## Variáveis de Ambiente para Azure
Existe a variável de ambiente que é indispensável para que a function funcione corretamente:
- AZURE_CREDENTIALS=<AZURE_CREDENTIALS> - Credenciais necessárias do usuário github para que possa realizar o deploy
- CLUSTER_NAME=<CLUSTER_NAME> - O nome do cluster que foi criado para o AKS
- CONFIG_MAP_YAML=<CONFIG_MAP_YAML> - ConfigMap necessário com as variáveis de ambiente para o funcionamento da aplicação
- RESOURSE_GROUP=<RESOURSE_GROUP> - Resouce Group destinado na Azure para a aplicação
- SUBSCRIPTION=<SUBSCRIPTION> - Subscription ao qual o AKS criado pertence
- PASSWORD_ACR=<PASSWORD_ACR> - Senha para enviar imagem no ACR
- SERVER_ACR=<SERVER_ACR> - Server criado para enviar imagem no ACR
- USERNAME_ACR=<USERNAME_ACR> - Usuário para enviar imagem no ACR

# Cloud
## Azure
Para a hospedagem de todo o projeto foi escolhida a Azure como Cloud pelos seguintes motivos:

- Extremamente robusta, segura e confiável
- Centraliza todos os recursos em Resouce Group, facilitando assim localizar tudo o que foi criado e ter melhor visualização dos custos da estrutura
- Transparente com os custos onde exibe de forma eficaz e clara onde está sendo usasdo o seu dinheiro e permite com muita agilidade interromper todos os recursos desnecessários e assim ajudando a economizar, diferente a AWS que faz questão de esconder muitos recursos criados que estão te cobrando.
- Tenho a conta de estudante que me permite utilizar R$ 500 para criar apresentar o projeto.

# Vídeo explicativo
## Fase 3
Segue link do vídeo que explica os seguintes pontos:
- Terraform criados para a estrutura que é montada
- Processo em cada um dos repositório com github actions
- Estrutura de login
- Estrutura Functions
- Estrutura Database
- Estrutura aplicação
- Demonstração do funcionamento da aplicação e do processo do login, realizando pedido tanto quando o CPF é informado e não informado

Link: https://youtu.be/v9yVQ3_HeSU

# Vídeo explicativo
## Fase 4
Segue link do vídeo que explica os seguintes pontos:
- Terraform readequado
- Estrutura Microsservices
- Nginx
- Estrutura testes
- Deploys AKS
- Sonar
- Estrutura bases
- Demonstração do funcionamento da aplicação em microsservices

Vídeo: https://youtu.be/4WYPlbg_rkY

# Alterações no Projeto
## Novos recursos
Para a conclusão da 4ª Fase do projeto foi necessário criar novos repositórios:
- Pagamento -> Mais detalhes acessar o [Readme](https://github.com/luisferrarezi/tiulanches-pagamento) do projeto.
- Pedidos -> Mais detalhes acessar o [Readme](https://github.com/luisferrarezi/tiulanches-pedido) do projeto.
- Produção -> Mais detalhes acessar o [Readme](https://github.com/luisferrarezi/tiulanches-producao) do projeto.

## Automação
Atualmente para esta branch existem 2 níveis de automação, explicado abaixo:

- Pull Request: este é executado no momento que é criado o PR, e nele é realizado um build para garantir que a aplicação não foi quebrada após alteração.
- Push: este é executado somente após o PR ter sido aprovado e executado o merge para a main, primeiro é realizado o build da aplicação, estando tudo ok  é então logado no Container Registry da Azure, criado a imagem como latest e faz o upload dela para o ACR. 
        Finalmente cria-se o ConfigMap com variáveis de ambiente necessárias para serem utilizadas pela aplicação no AKS, feito isso são utilizados os yamls configmap.yaml e app-deployment.yaml, para subir a aplicação no AKS.

## Variáveis de Ambiente para Azure
Novas variáveis de ambiente que são indispensáveis para que funcione corretamente:
- CONEXAO_KAFKA=<CONEXAO_KAFKA> - Configuração do service do kafka
- SONAR_TOKEN=<SONAR_TOKEN> - Token criado no sonar para que a aplicação seja validada por ele

## Sonar
Foi implementado no através do git actions execução de testes e validação via sonar de todos os PRs e Main.

Segue imagem que ilustra o coverage e validação do sonar para este projeto:

![](https://github.com/luisferrarezi/tiulanches/blob/main/documentacao/imagens/coverage.jpg?table=block&id=ea599cfc-189c-4b5a-b3eb-21db292154fe&spaceId=62941c71-5c2d-41d6-8c4f-a5f5b14de56c&width=2000&userId=&cache=v2)

## Arquitetura
Abaixo a imagem que ilustra a arquitetura atual do projeto usando microsserviços:

![](https://luisferrarezi.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F62941c71-5c2d-41d6-8c4f-a5f5b14de56c%2F67119464-361b-4c01-9d4d-c8953d75b26f%2FUntitled.png?table=block&id=f42cce93-a93f-4e1a-8cda-e28417bd1d97&spaceId=62941c71-5c2d-41d6-8c4f-a5f5b14de56c&width=1820&userId=&cache=v2)

# Vídeo explicativo
## Fase 5
Segue link do vídeo que explica os seguintes pontos:
- Padrão SAGA utilizado
- Relatórios OWASP ZAP
- RIPD
- Demonstraçao do duncionamento da arquitetura utilizando o padrão SAGA

Vídeo: 

# Alterações no Projeto
## Novos recursos
Para a conclusão da 5ª Fase do projeto foi necessário alterar o comunicação entre as aplicações repositórios:
- Pagamento -> Mais detalhes acessar o [Readme](https://github.com/luisferrarezi/tiulanches-pagamento) do projeto.
- Pedidos -> Mais detalhes acessar o [Readme](https://github.com/luisferrarezi/tiulanches-pedido) do projeto.
- Produção -> Mais detalhes acessar o [Readme](https://github.com/luisferrarezi/tiulanches-producao) do projeto.

## OWASP ZAP
Nesta fase por ter sido executada a aplicação ZAP para identificar as vulnerabilidades dentro dos repositórios podem ser encontrados seus relatórios, são eles:
- Pagamento
- Cadastro
- Pedidos

Segue abaixo o correspondente aos cadastros:

[Lista produtos antes correção](https://github.com/luisferrarezi/tiulanches/blob/main/documentacao/owasp/zap/ZAP-Lista-Produtos.pdf)

[Lista produtos após correção](https://github.com/luisferrarezi/tiulanches/blob/main/documentacao/owasp/zap/ZAP-Lista-Produtos-Corrigido.pdf)

## Execução
Antes de se executar qualquer comando deste projeto é necessário que esse sejam executados os comandos que estão no repositório que tem toda a especificação para ter uma estrutura prévia. [Tiu Lanches - Terraform app](https://github.com/luisferrarezi/tiulanches-app-terraform).

Para executar o projeto localmente é necessário que antes seja criada uma base Mysql e configurar as variáveis de ambiente especificadas em "Variáveis de Ambiente", após isso vá até kubernetes\local onde tem o deployment necessário:

~~~Execute
sh startapp.sh
~~~

Neste bash tem as instruções em que serão executados os deployments na ordem e tempo correto

## RIPD
Foi criado um Relatório de Impacto à Proteção de Dados Pessoais (RIPD), baseado em um modelo repassado pela FIAP

[RIPD](https://github.com/luisferrarezi/tiulanches/blob/main/documentacao/lgpd/RIPD.pdf)

## Arquitetura
A arquitetura está específicada neste mesmo Readme, e é o mesmo que o divulgado para a fase 4.

## SAGA
O padrão SAGA que escolhi para este projeto é o coreografado, pelas seguintes razões:
- O projeto foi todo arquitetado com Kafka que é uma ferramenta muito confiável e robusta que permite comportar este padrão
- Para manter cada microsserviço com sua devida responsabilidade
- Manter projetos independentes e descentralizados

Para poder atender este padrão os projetos foram alterados de forma que os cadastros atualmente precisarão receber a confirmação de que suas informações foram devidamente cadastradas em todos os demais microsserviços quando necessário. E dessa forma garantir a consistência dos dados entre as aplicações. O Kafka tem um papel fundamental nessa replicação de dados, porque quando uma aplicação que precisa receber uma mensagem está fora, ao subir novamente ela recebe todas as mensagens pendentes para ela e assim atualiza as informações devidamente.
