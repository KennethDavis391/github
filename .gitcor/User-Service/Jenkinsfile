pipeline {
  environment {
          registry = 'javasre2022/userservice'
          dockerHubCreds = 'docker_hub'
          dockerImage = ''
    }
  agent {
    kubernetes {
    label 'user-service-pod' 
    idleMinutes 5 
    yamlFile 'build-pod.yaml'  
    defaultContainer 'maven'  
    }
  }
  stages {
    stage('Test') {
      steps {
        sh 'mvn -f pom.xml test'
      }
    }

  stage('Build') {
            steps {
              withMaven {
                sh 'mvn -f pom.xml clean install'
                sh 'mvn -f pom.xml clean package -DskipTests'
              }
            }
   }

   stage('Docker Build') {
     steps {
       script {
         echo "$registry:$currentBuild.number"
         dockerImage = docker.build ("$registry", "-f Dockerfile .")
       }
     }
   }

   stage('Docker Deliver') {
        steps {
          script {
            docker.withRegistry('',dockerHubCreds) {
                      dockerImage.push()
            }
          }
        }
      }
  //
  //     stage('Deploy to GKE') {
  //
  //             steps{
  //                sh 'sed -i "s/%TAG%/$BUILD_NUMBER/g" ./k8s/deployment.yml'
  //                sh 'cat ./k8s/deployment.yml'
  //                step([$class: 'KubernetesEngineBuilder',
  //                     projectId: 'active-road-343813',
  //                     clusterName: 'active-road-343813-gke',
  //                     zone: 'us-central1',
  //                     manifestPattern: 'k8s/',
  //                     credentialsId: 'active-road-343813',
  //                     verifyDeployments: true
  //                ])
  //
  //                cleanWs()

  //                discordSend description: "Build #$currentBuild.number",
  //                     link: BUILD_URL, result: currentBuild.currentResult,
  //                     title: JOB_NAME,
  //                     webhookURL: "https://discord.com/api/webhooks/946097550514061343/7IRGxvAsw24cbGPIHXE15gtxCvzQQtRl3e5DEcm7arQpC6x3cVJPXXWZo7UWHKyJumuW"
  //             }
  //             }
      }
  }