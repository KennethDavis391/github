pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('CheckMVN') {
          when {
            branch 'master'
          }
          steps {
            withMaven() {
              sh 'echo $MVN_HOME'
            }

          }
        }

        stage('CheckJAVA') {
          steps {
            sh 'echo $JAVA_HOME'
          }
        }

      }
    }

  }
}