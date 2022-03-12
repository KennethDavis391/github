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

        stage('CheckMVN') {
          steps {
            sh 'sh \'mvn --version\''
          }
        }

      }
    }

  }
}