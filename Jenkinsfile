pipeline {
  agent none
  stages {
    stage('Test') {
      parallel {
        stage('Test') {
          steps {
            echo '1'
          }
        }

        stage('Test2') {
          steps {
            echo '2'
          }
        }

        stage('Test3') {
          steps {
            echo '3'
          }
        }

      }
    }

    stage('Verify') {
      parallel {
        stage('Verify') {
          steps {
            echo 'A'
          }
        }

        stage('Verify2') {
          steps {
            echo 'B'
          }
        }

      }
    }

  }
}