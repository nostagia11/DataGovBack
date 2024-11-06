pipeline {
   agent any
   tools {
       jdk 'JAVA_HOME'
       maven 'M2_HOME'
   }

   environment {
      // MAVEN_CREDENTIALS_ID = 'jenkins-nexus'
      // MAVEN_REPO_URL = 'http://192.168.33.10:8081/repository/Jenkins-repository/'
       DOCKER_CREDENTIALS_ID = 'docker-cred'
       IMAGE_NAME = 'nostqgiq11/datagov'
       IMAGE_TAG = '1.0.0-SNAPSHOT'
   }

   stages {
       stage("Cleanup Workspace") {
           steps {
               cleanWs()
           }
       }
       stage('Checkout from GIT') {
           steps {
           echo 'pulling ... ' ;
               git branch: 'master', url: 'https://github.com/nostagia11/DataGovBack.git'
           }
       }

       stage("Maven Clean") {
                  steps {
                      sh "mvn clean "
                  }
              }
       stage("Maven Compile") {
                  steps {
                      sh "mvn compile"
                  }
              }
       stage("Build Application") {
           steps {
               sh "mvn package"
           }
       }

       stage("Sonarqube Analysis") {
           steps {
               script {
                   withSonarQubeEnv('sonar-server') {
                       sh "mvn sonar:sonar"
                   }
               }
           }
       }


       stage("Build Docker Image") {
           steps {
               script {
                   withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                       sh '''
                           docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
                       '''
                   }
               }
           }
       }
       stage("Push Docker Image") {
           steps {
               script {
                   withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                       sh '''
                           echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
                           docker push ${IMAGE_NAME}:${IMAGE_TAG}
                       '''
                   }
               }
           }
       }

   }
}