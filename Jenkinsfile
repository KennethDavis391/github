pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh 'echo Good-moring'
        withMaven {
            sh 'maven is good to go'
        }
      }
    }

  }
}