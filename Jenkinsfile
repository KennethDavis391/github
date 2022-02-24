pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh 'echo Goodmoring'
        withMaven {
            sh 'maven is good to go'
        }
      }
    }

  }
}