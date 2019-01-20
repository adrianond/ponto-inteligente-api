pipeline {
    agent any
    
    stages {
       stage ('Compilando o projeto') {
           steps {
             WithMaven(maven : 'maven-3.5.2') {
             sh 'maven clean compile'
             }
           }
       }
       
       stage ('Executando os testes Unitários') {
          steps {
             WithMaven(maven : 'maven-3.5.2') {
             sh 'maven test'
             }
           }                 
        }
        
        stage ('Efetuando o build do projeto') {
          steps {
             WithMaven(maven : 'maven-3.5.2') {
             sh 'maven deploy'
             }
           }                 
        }
    }
}