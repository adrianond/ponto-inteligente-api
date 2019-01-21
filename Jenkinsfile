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
        
       stage('Deploy em Prod'){
         steps {
               //bat "mvn heroku buildpacks:set https://github.com/adrianond/ponto-inteligente-api.git"
                bat " mvn clean heroku:deploy https://git.heroku.com/ponto-inteligente-app.git"
                //bat " mvn heroku buildpacks:clear --app ponto-inteligente -DskipTests=true -Dmaven.javadoc.skip=true -B -V -D heroku.appName=https://git.heroku.com/ponto-inteligente-app.git"
                
                
            }
        }
    } 

}