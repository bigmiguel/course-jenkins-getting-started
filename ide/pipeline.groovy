pipeline {
    agent any
  
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/bigmiguel/jgsu-spring-petclinic.git', branch: 'main'
            }            
        }
        stage('Build') {
            steps {
                sh './mvnw clean package'
                //sh 'false' // true
            }
        
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'                }
             
            }
        }
    }
}
