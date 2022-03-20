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

      }
    }
    stage('test/staging') {
    definition {
        properties {
            disableConcurrentBuilds()
        }
        parameters {
            stringParam('COMPONENT', null, 'backend/frontend')
            stringParam('SEMANTIC_VERSION', null, '1.0.36')
        }
        cps {
            script(readFileFromWorkspace('jobs/test/staging.Jenkinsfile'))
            sandbox()
        }
      }
    }

    stage('dostuff') {
      steps {
        withSonarQubeEnv(credentialsId: 'c9d3cafaced0a6afc8bb4d687753058265ba3ec4', installationName: 'blackjacksonar') {
          echo 'Hello1'
          echo 'Hello2'
          echo 'Hello3'
//           cps {             script(readFileFromWorkspace('jobs/test/staging.Jenkinsfile'))             sandbox()         }
        }

      }
    }

  }
}
