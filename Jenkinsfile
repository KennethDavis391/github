pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh 'echo Good-moring'
        sh 'apt-get install -y maven'
        withMaven {
            sh 'maven is good to go'
        }
      }
    }

  }
}