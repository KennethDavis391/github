pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh 'User root'
        sh 'sudo apt-get install -y maven'
        sh 'User jenkins'
      }
    }

  }
}