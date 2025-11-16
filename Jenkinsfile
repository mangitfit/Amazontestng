pipeline {
    agent {
            docker {
                image 'selenium/standalone-chrome:latest'
                args '--user root -v /var/jenkins_home/workspace/testng-automation:/workspace'
            }
        }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/mangitfit/Amazontestng.git',
                    credentialsId: 'github-token'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }

    post {
        always {
            echo "Build completed - check test results!"
        }
    }
}