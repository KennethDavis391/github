pipeline {

  environment {
    PROJECT = "javasrc"
    APP_NAME = "mongo"
    FE_SVC_NAME = "${APP_NAME}-frontend"
    CLUSTER = "jenkins-cd"
    CLUSTER_ZONE = "us-east1-d"
    IMAGE_TAG = "gcr.io/${PROJECT}/${APP_NAME}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"
    JENKINS_CRED = "${PROJECT}"
  }

  agent {
    kubernetes {
      yaml """
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mongo-deployment
  labels:
    app: nenaMongo
spec:
  replicas: 1
  selector:
    matchLabels:
      app: nenaMongo
  template:
    metadata:
      labels:
        app: nenaMongo
    spec:
      containers:
      - name: mongodb
        image: mongo:5.0
        ports:
        - containerPort: 27017
        env:
        - name:  MONGO_INITDB_ROOT_USERNAME
          valueFrom:
             secretKeyRef:
                name:   mongo-secret
                key:   mongo-user
        - name:  MONGO_INITDB_ROOT_PASSWORD
          valueFrom:
             secretKeyRef:
                name:   mongo-secret
                key:   mongo-password
"""
}
  }
  stages {
    stage('Test') {
      steps {
        echo T1p2
      }
    }
    stage('config') {
      steps {
        sh 'kubectl apply -f mongo-config.yaml'
        sh 'kubectl apply -f mongo-secret.yaml'
      }
    }
    stage('mongo') {
      steps {
        sh 'kubectl apply -f m-ongo-s.yaml'
      }
    }
    stage('check') {
      steps {
        sh 'kubectl get all -o wide'
      }
    }
    stage('Built') {
      steps {
        echo jenkinsEnhanced
      }
    }

  }
}
