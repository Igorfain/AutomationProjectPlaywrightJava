pipeline {
    agent any
    tools {
        jdk 'Java 17'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Allure Report') {
            steps {
                sh 'allure generate allure-results --clean -o allure-report'
            }
        }
        stage('Debug Workspace') {
            steps {
                sh 'ls -lR'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'allure-results/**/*', allowEmptyArchive: true
            allure includeProperties: false, jdk: '', reportBuildPolicy: 'ALWAYS', results: [[path: 'allure-results']]
        }
        failure {
            echo 'Build failed. Check the logs for details.'
        }
    }
}
