pipeline {
    agent any
    tools {
        maven 'maven-3.5.2' 
    }
    
    stages {
       stage ('Compilando o projeto') {
           steps {
             //maven(maven : 'maven-3.5.2') {
             //sh 'maven clean compile'
             bat "mvn clean compile"
            // }
           }
       }
       
       stage ('Executando os testes Unitarios') {
          steps {
             //WithMaven(maven : 'maven-3.5.2') {
             //sh 'maven test'
             bat "mvn test"
             //}
           }                 
        }
        
        stage ('Efetuando o build do projeto') {
          steps {
            // WithMaven(maven : 'maven-3.5.2') {
             //sh 'maven deploy'
             bat "mvn deploy"
             //}
           }                 
        }
    }
    
    post {
    failure {
        mail to: 'adrianond@yahoo.com.br',
            subject: 'Falha no Build do projeto ponto-inteligente-pipeline',
            body: "Ocorreu falha no build/deploy do projeto ponto-inteligente-pipeline!"
    }
     success {
           mail to: 'adrianond@yahoo.com.br',
            subject: 'Build do projeto ponto-inteligente-pipeline',
            body: "Deploy do projeto ponto-inteligente-pipeline realizado com sucesso"
        }
    }
}