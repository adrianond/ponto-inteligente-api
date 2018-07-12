[![Build Status](https://travis-ci.org/m4rciosouza/ponto-inteligente-api.svg?branch=master)](https://travis-ci.org/m4rciosouza/ponto-inteligente-api)
# Ponto Inteligente
API do sistema de ponto inteligente com Java e Spring Boot.
### Detalhes da API RESTful
A API RESTful de Ponto Inteligente contém as seguintes características:  
* Projeto criado com Spring Boot e Java 8
* Banco de dados MySQL com JPA e Spring Data JPA
* Autenticação e autorização com Spring Security e tokens JWT (JSON Web Token)
* Migração de banco de dados com Flyway
* Testes unitários e de integração com JUnit e Mockito
* Caching com EhCache
* Integração contínua com TravisCI
### Como executar a aplicação
Certifique-se de ter o Maven instalado e adicionado ao PATH de seu sistema operacional, assim como o Git.
```
git clone https://github.com/m4rciosouza/ponto-inteligente-api.git
cd ponto-inteligente-api
mvn spring-boot:run
Acesse os endpoints através da url http://localhost:8080
```
### Importando o projeto no Eclipse ou STS
No terminal, execute a seguinte operação:
```
mvn eclipse:eclipse
```
No Eclipse/STS, importe o projeto como projeto Maven.


Arquivo V1_init.sql - arquivo que contém as queries de criação das tabelas, alteração....
flyway para automatizar a criação de um banco de dados : O Flyway é um framework que permite o versionamento e automatização no processo de
criação​ ​de​ ​banco​ ​de​ ​dados.
Nele é possível configurar a criação de tabelas, dados iniciais que devem estar na base de dados,​ ​entre​ ​outros
Para criar as tabelas: basta executar a aplicação para que o Spring Boot execute o Flyway automaticamente​ ​e​ ​gere​ ​nossa​ ​tabela​ ​na​ ​base​ ​de​ ​dados

H2 - banco de dados em memoria, utilizo ele para executar os testes unitários das minhas queries.
application-test.properties(profile de teste) - faço a configuração do H2

A anotação @Component é um tipo genérico para qualquer bean que deve ser gerenciado pelo Spring.

@Repository, @Service e @Controller são especializações de @Component e servem para as camadas de persistencia, serviço e apresentação respectivamente.

Você pode utilizar @Component para qualquer classe da sua aplicação, mas utilizar uma anotação mais específica ajuda caso deseje criar um filtro, utilizar ferramentas, ou orientação a aspectos.

Funcionalmente todas as anotações servem para declarar beans, não existe diferença entre elas a não ser na questão de organização da apliacação e utilidades citadas anteriormente.

Como utulizamos spring security, é necessario autenticar os usuarios antes que fazerem chamadas a URL's protegidas e que necessitam de autenticação.
Isso é feito fazendo uma chamada POST no AuthenticationController e utilizando a URL /auth passando um email e senha validos, assim faço a requisição 
que deve retornar sucesso e o token, então copio sem as apas o token para autenticar na aplicação, retorno na requisição que tentei executar, clico ma aba header,
adiciono key:Autorization, value: Bearer 'meuToken'


Se eu tentar executar um delete com um usuário sem roler Admin, é necessário antes autenticar com um usuário com esse papel de admin

A ação de login na aplicação, que dependede três arquivos, um​ ​DTO​ ​para​ ​o​ ​usuário,​ ​um​ ​DTO​ ​para​ ​retornar​ ​o​ ​token,​ ​e​ ​o​ ​controller​ ​de​ ​autenticação

@RunWith(SpringRunner.class) e @SpringBootTest serve para o Spring criar um contexto de teste

V2__admin_padrao.sql - usado para criar um usuario padrão para o swagger, pois é preciso autenticar um usuario, então utilizo esse usuario padrao para autentica - lo
