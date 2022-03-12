pipeline {
  agent any
  stages{
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
  }
}
