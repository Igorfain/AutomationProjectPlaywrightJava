pipeline {
    agent any

    tools {
        maven 'Maven' // Use the name you configured in Global Tool Configuration
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
                sh 'allure generate target/allure-results --clean -o target/allure-report'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/allure-results/**/*', allowEmptyArchive: true
            archiveArtifacts artifacts: 'target/allure-report/**/*', allowEmptyArchive: true
        }

        failure {
            echo 'Build failed. Check the logs for more details.'
        }
    }
}
