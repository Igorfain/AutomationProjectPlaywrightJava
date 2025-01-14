pipeline {
    agent any

    tools {
        jdk 'Java 17' // Use the name you configured in Global Tool Configuration
    }

    stages {
        stage('Setup Environment') {
            steps {
                sh '''
                    apt-get update
                    apt-get install -y libxkbcommon0 libgbm1
                    apt-get install -y allure
                '''
            }
        }

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
        }

        failure {
            echo 'Build failed. Check the logs for details.'
        }
    }
}
