pipeline {
   agent any
   tools {
       jdk 'JAVA_HOME'
       maven 'M2_HOME'
   }

   environment {
       MAVEN_CREDENTIALS_ID = 'nexus-cred'
       MAVEN_REPO_URL = 'http://192.168.192.142:8081/repository/maven-releases/'
       DOCKER_CREDENTIALS_ID = 'docker-cred'
       IMAGE_NAME = 'nostagia11/datagov'
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
               sh "mvn package -DskipTests=true"
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


       stage("Deploy to Nexus") {
           steps {
               script {
                   withCredentials([usernamePassword(credentialsId: env.MAVEN_CREDENTIALS_ID, usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                       sh """
                           mvn deploy:deploy-file \
                           -DgroupId=tn.esprit \
                           -DartifactId=datagov \
                           -Dversion=1.0-SNAPSHOT \
                           -Dpackaging=jar \
                           -Dfile=target/datagov-1.0.0-SNAPSHOT.jar \
                           -DrepositoryId=deploymentRepo \
                           -Durl=${env.MAVEN_REPO_URL} \
                           -Dusername=$USERNAME \
                           -Dpassword=$PASSWORD
                       """
                   }
               }
           }
       }

       stage("Deploy with Docker Compose") {
               steps {
                   script {
                       withCredentials([usernamePassword(credentialsId: env.DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                           sh '''
                               docker-compose down
                               docker-compose pull
                               docker-compose up -d
                           '''
                       }
                   }
               }
       }
   }