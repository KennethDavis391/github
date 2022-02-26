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
      label 'the-mongo'
      defaultContainer 'jnlp'
      yaml """
#-------deployment and services grouped together here in one file
#-------deployment----------
apiVersion: apps/v1
kind: Deployment
#-------metadate and specs----------
metadata:
  name: mongo-deployment
  labels:
    app: nenaMongo
spec:
  replicas: 1
  #-------selector----------defines which pods belong to this deployment
  selector:
    matchLabels:
      app: nenaMongo
  template:
  #-------metadate and specs----------each pod gets a unique name, however they share the same label
    metadata:
      labels:
        app: nenaMongo
    spec:
      containers:
      #-------Containers--------------------lookup the desired image on dockerhub or any repository
      #-------based on the image, find out the port it usually runs on from documentation
      #-------based on the database type, find out the username and pass variables from documentation
      #-------then you need to pass environment variables in this configuration file
      #-------reference Secret data from the mongo-secret.yaml file
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
---
#---------service INTERNAL----------
apiVersion: v1
kind: Service
metadata:
  name: mymongo-service
spec:
  type: ClusterIP
  selector:
    app: nenaMongo
  ports:
    - protocol: TCP
      port: 8080
      targetPort: 27017
      nodePort: 32767
#-------------the ports for the pod and the service targetPort must match
#-------------the host port 8080 could be anything, it could even match the pod and service port
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
        sh 'kubectl apply -f m-ongo.yaml'
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
