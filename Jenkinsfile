pipeline {
   environment {
       registry = 'ajduet/sre-demo'
       dockerHubCreds = 'docker_hub'
       dockerImage = ''
       deploymentFile = 'k8s/deployment.yml'
    }
  agent any
  stages {
    stage('Quality Gate') {
        steps {
            withSonarQubeEnv(credentialsId: 'sonarqube', installationName: 'sonarqube') {
                withMaven {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
    }
    stage('Test') {
      when {
        branch 'feature/*'
      }
      steps {
        withMaven {
            sh 'mvn test'
        }
      }
    }
    stage('Build') {
        when {
            branch 'main'
        }
        steps {
            withMaven {
                sh 'mvn clean package -DskipTests'
            }
        }
    }
    stage('Docker Build') {
        when {
            branch 'main'
        }
        steps {
            script {
                echo "$registry:$currentBuild.number"
                dockerImage = docker.build "$registry"
            }
        }
    }
    stage('Docker Deliver') {
        when {
            branch 'main'
        }
        steps {
            script {
                docker.withRegistry('', dockerHubCreds) {
                    dockerImage.push("$currentBuild.number")
                    dockerImage.push("latest")
                }
            }
        }
    }
    stage('Wait for approval') {
        when {
            branch 'main'
        }
        steps {
            script {
                try {
                    timeout(time: 1, unit: 'MINUTES') {
                        approved = input message: 'Deploy to production?', ok: 'Continue',
                                           parameters: [choice(name: 'approved', choices: 'Yes\nNo', description: 'Deploy build to production')]
                        if(approved != 'Yes') {
                            error('Build did not pass approval')
                        }
                    }
                } catch(error) {
                    error('Build failed because timeout was exceeded')
                }
            }
        }
    }
    stage('Deploy to GKE') {
        when {
            branch 'main'
        }
        steps{
           sh 'sed -i "s/%TAG%/$BUILD_NUMBER/g" ./k8s/deployment.yml'
           sh 'cat ./k8s/deployment.yml'
            step([$class: 'KubernetesEngineBuilder',
                projectId: 'javasre',
                clusterName: 'javasre-gke',
                zone: 'us-east1',
                manifestPattern: 'k8s/',
                credentialsId: 'javasre',
                verifyDeployments: true
            ])

            cleanWs()            
        }
    }
  }
}
