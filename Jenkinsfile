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

        stage('Setup Environment') {
                    steps {
                        sh '''
                            echo "=== Checking Java ==="
                            java -version
                            echo "=== Checking Maven ==="
                            if ! command -v mvn &> /dev/null; then
                                echo "Maven not found, installing..."
                                apt-get update
                                apt-get install -y maven
                            fi
                            mvn -version
                        '''
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