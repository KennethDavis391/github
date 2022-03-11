pipeline {
  agent any
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
