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
```eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBrYXphbGUuY29tIiwicm9sZSI6IlJPTEVfQURNSU4iLCJjcmVhdGVkIjoxNTQ3ODE3NDUzODA3LCJleHAiOjE1NDg0MjIyNTN9.kIwFGD7V6a0zhov0otWZrWzKsoC6oa0_p0gpENG-rArZUZ51Ns78lRy2zYShfsypnThiZRkxS6pC02fEVUMmmA
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

------------------------------------------------------------------------------------------------------------------------------------------------------
H2 - banco de dados em memoria, utilizo ele para executar os testes unitários das minhas queries.
application-test.properties(profile de teste) - faço a configuração do H2

--------------------------------------------------------------------------------------------------------------------------------------------------------
A anotação @Component é um tipo genérico para qualquer bean que deve ser gerenciado pelo Spring.

@Repository, @Service e @Controller são especializações de @Component e servem para as camadas de persistencia, serviço e apresentação respectivamente.

Você pode utilizar @Component para qualquer classe da sua aplicação, mas utilizar uma anotação mais específica ajuda caso deseje criar um filtro, utilizar ferramentas, ou orientação a aspectos.

Funcionalmente todas as anotações servem para declarar beans, não existe diferença entre elas a não ser na questão de organização da apliacação e utilidades citadas anteriormente.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
Como utulizamos spring security, é necessario autenticar os usuarios antes que fazerem chamadas a URL's protegidas e que necessitam de autenticação.
Isso é feito fazendo uma chamada POST no AuthenticationController e utilizando a URL /auth passando um email e senha validos, assim faço a requisição 
que deve retornar sucesso e o token, então copio sem as apas o token para autenticar na aplicação, retorno na requisição que tentei executar, clico ma aba header,
adiciono key:Autorization, value: Bearer 'meuToken'

Se eu tentar executar um delete com um usuário sem roler Admin, é necessário antes autenticar com um usuário com esse papel de admin

A ação de login na aplicação, que dependede três arquivos, um​ ​DTO​ ​para​ ​o​ ​usuário,​ ​um​ ​DTO​ ​para​ ​retornar​ ​o​ ​token,​ ​e​ ​o​ ​controller​ ​de​ ​autenticação

---------------------------------------------------------------------------------------------------------------------------------------------------------------------
@RunWith(SpringRunner.class) e @SpringBootTest serve para o Spring criar um contexto de teste

V2__admin_padrao.sql - usado para criar um usuario padrão para o swagger, pois é preciso autenticar um usuario, então utilizo esse usuario padrao para autentica - lo

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
HEROKU :
O arquivo Procfile (na raiz da aplicação) é para informar ao heroku para usar o profile de produção, e qual jar(da nossa aplicação) será usado pelo heroku para executar a aplicação

URL do projeto no heroku :  https://young-thicket-77956.herokuapp.com (neste caso vai dar erro, pois nosso projeto trata - se de uma API),
logo para testar nossa API no heroku, utilizo o Postman, mas claro que trocando a URL(localhost),pela URl do heroku ( https://young-thicket-77956.herokuapp.com)+recurso REST, por exemplo :https://young-thicket-77956.herokuapp.com/api/funcionarios/3


Para enviar projeto para o heroku:
1. criar o app no heroku (pod fazer a inteface do heroku, ou pela linha de comando)

2.efetuar login heroku:
er Heroku: adrianond@yahoo.com.br
senha: Nogueira_1

3. Agora é preciso associar seu repositorio local(seu fonte) com a máquina virtual do heroku
3.1.Acesse pelo terminal a raiz do projeto a ser enviado para o Heroku execute o comando:
3.2.heroku git:remote -a ponto-inteligente-app (este ultimo é nome do app criado no heroku
4.git​ ​add​ ​. 
5.git​ ​commit​ ​-m​ ​​"Primeiro​ ​commit"  
5.git​ ​push​ ​heroku​ ​master 
7.heroku​ ​open (se quiser abrir o heroku)

No fim do deploy é informado a URL do projeto no heroku, neste caso:
https://ponto-inteligente-app.herokuapp.com

OBS: como uso BD na palicação ponto- inteligente, será preciso criar um BD no heroku
1.clicar em Overview
2.clicar em Configure Add-ons
3. no campo de buscar digitar Clear DB (banco de dados MySQL para nuvem do heroku)
4.agora será necessário obter a URL desse BD, execute o comando no console (ainda na pasta do projeto)
5.heroku config | grep CLEARDB_DATABASE_URL - esta URL será informada no application-prop.properties
6.no arquivo Procfile está sendo informado que é para usar as configurações do arquivo application-prop.properties

-------------------------------------------------------------------------------------------------------------------------------------------------------
Acessar banco de dados : http://localhost:8080/phpmyadmin/

Classe que habilita/desabilita a autenticação : WebSecurityConfig


