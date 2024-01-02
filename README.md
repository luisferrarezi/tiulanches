# TIU LANCHES
| :placard: Vitrine.Dev |     |
| -------------  | --- |
| :sparkles: Nome        | **Tiu Lanches**
| :label: Tecnologias | Java, Maven, Spring, MySQL 
| :rocket: URL         | 
| :fire: Desafio     | Tech Challenge FIAP

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) ![Static Badge](https://img.shields.io/badge/any_text-Version-blue?label=23.12.04)

<!-- Inserir imagem com a #vitrinedev ao final do link -->
![](https://www.notion.so/image/https%3A%2F%2Fimagens.jotaja.com%2Fempresa%2Ffcf91c6a-1626-4412-b5d0-845c777d5611.jpg?table=block&id=818bd35f-516d-459d-9525-f3bc2f7c2af6&spaceId=62941c71-5c2d-41d6-8c4f-a5f5b14de56c&width=2000&userId=06b981be-eaf4-4de6-9a12-a77aa351d285&cache=v2#vitrinedev)

## Detalhes do projeto
Projeto criado para conclusão da Pós Gradução em Software Architectura pela FIAP

# Documentação Projeto
[Projeto no Notion](https://luisferrarezi.notion.site/Tiu-Lanches-818bd35f516d459d9525f3bc2f7c2af6)

# Pattern
- Clean Architecture

# Linguagem
- Java - JDK 21

# Banco de dados
- MySql - 8.0

# Frameworks utilizados 
- Mercado Pago SDK - 2.1.17
- Spring Framework - 3.2.0
- Lombok
- Flyway
- Maven 
- Jackson Databind
- Log4j
- Spring Doc

# Variáveis de Ambiente
Para poder funcionar é necessário cadastrar as seguintes variáveis de ambiente na sua IDE ou SO:
- DATASOURCE_URL=<URL_CONEXAO_BD> 
- DATASOURCE_USERNAME=<USER_NAME_DB> 
- DATASOURCE_PASSWORD=<SENHA_DB>

# Acessos 
- Aplicação: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui/index.html
- ReDoc: http://localhost:8080/redoc.html

# Subir Aplicação Docker
- Baixe o docker compose do projeto em https://github.com/luisferrarezi/tiulanches/blob/main/compose/docker-compose.yml

~~~Execute
docker compose -f docker-compose.yml up --build -d
~~~
