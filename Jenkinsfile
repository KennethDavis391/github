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

        stage('DeployToCluster') {
          steps {
            withKubeConfig(credentialsId: '3e525453-366d-497b-9686-c1f8af64d6a5', serverUrl: 'https://35.232.148.254') {

              sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.20.5/bin/linux/amd64/kubectl"'  
              sh 'chmod u+x ./kubectl'  
              sh './kubectl get pods'
              sh './kubectl apply -f 2.yml'
            }

          }
        }

      }
    }

  }
}
