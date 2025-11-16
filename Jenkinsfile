pipeline {
    agent any

    stages {
        // Stage 1: Get the latest test code
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/mangitfit/Amazontestng.git',
                    credentialsId: 'github-token'
            }
        }

        // Stage 2: Run Tests
        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    // Stage 3: Report Results (runs after all stages)
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}