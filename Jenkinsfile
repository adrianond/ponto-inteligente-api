pipeline {
    agent any
    
    stages {
       stage ('Compile stage') {
           steps {
             WithMaven(maven : 'maven-3.5.2') {
             sh 'maven clean compile'
             }
           }
       }
       
       stage ('Testing stage') {
          steps {
             WithMaven(maven : 'maven-3.5.2') {
             sh 'maven test'
             }
           }                 
        }
        
        stage ('Deployment stage') {
          steps {
             WithMaven(maven : 'maven-3.5.2') {
             sh 'maven deploy'
             }
           }                 
        }
    }
}