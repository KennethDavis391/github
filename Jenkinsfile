pipeline {
  agent any
  stages {
    stage('CheckJAVA') {
      parallel {
        stage('CheckJAVA') {
          steps {
            sh 'echo $JAVA_HOME'
          }
        }

        stage('CheckDocker') {
          steps {
            sh 'docker run --help'
          }
        }

        stage('CheckDockerAgain') {
          steps {
            sh 'docker pull jschuwan/jschuwan:bc485abe7f7b4f47a11eba36c84169a8'
          }
        }

        stage('CheckMVN') {
          steps {
            sh 'mvn --version'
          }
        }

        stage('Apply Kubernetes files') {
          steps {
            withKubeConfig(credentialsId: 'fed8dee1-b7f0-46ea-bf90-694bcb21019f', serverUrl: 'https://34.123.116.172') {
              sh 'kubectl apply -f my-kubernetes-directory'
//               sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.20.5/bin/linux/amd64/kubectl"'  
//               sh 'chmod u+x ./kubectl'  
//               sh './kubectl get pods'
            }

          }
        }

      }
    }

  }
}
