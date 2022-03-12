pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          when {
            branch 'master'
          }
          steps {
            withMaven() {
              sh 'mvn clean package'
            }

          }
        }

        stage('installshitplease') {
          steps {
            sh 'apt-get install update'
          }
        }

      }
    }

  }
}