pipeline {
    agent any

    tools {
        jdk 'Java 17' // Use the name you configured in Global Tool Configuration
    }

    stages {
        stage('Setup Environment') {
            steps {
                sh '''
                    # Update package lists
                    sudo apt-get update

                    # Install missing dependencies for Playwright
                    sudo apt-get install -y libxkbcommon0 libgbm1

                    # Install unzip for handling Allure
                    sudo apt-get install -y unzip

                    # Download and install Allure CLI
                    curl -o allure.zip -L https://github.com/allure-framework/allure2/releases/latest/download/allure-2.21.0.zip
                    unzip allure.zip -d /opt/allure
                    ln -sf /opt/allure/allure-2.21.0/bin/allure /usr/local/bin/allure
                    chmod +x /usr/local/bin/allure
                    rm -f allure.zip

                    # Verify Allure installation
                    allure --version
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
            archiveArtifacts artifacts: 'target/allure-results/**/*, target/allure-report/**/*', allowEmptyArchive: true
        }

        failure {
            echo 'Build failed. Check the logs for details.'
        }
    }
}
