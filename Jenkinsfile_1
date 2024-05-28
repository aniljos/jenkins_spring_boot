pipeline {
    agent any

    tools {
        // Define the JDK and Maven installations from the Global Tool Configuration
        jdk 'JDK21'
        //maven 'Maven 3.6.3'
    }

    environment {
        // Define any environment variables
        //DOCKER_IMAGE = 'app-spring-services:latest'
        IMAGE_NAME = 'spring-app'
        IMAGE_TAG = 'latest'
        POSTGRES_DB = 'training'
        POSTGRES_USER = 'postgres'
        POSTGRES_PASSWORD = 'sa'
        SAVE_PATH = 'D:\\Jenkins\\Spring\\docker-images'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the Git repository
                git branch: 'main', url: 'https://github.com/aniljos/jenkins-spring-sample'
            }
        }
        stage('Build') {
            steps {
                // Build the project using Maven
                bat 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                // Run tests using Maven
                bat 'mvn test'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image
                    //bat "docker build -t ${DOCKER_IMAGE} ."
                    docker.build("${env.IMAGE_NAME}:${env.IMAGE_TAG}")
                }
            }
        }
        stage('Deploy') {
            steps {
                 bat "docker-compose down"
                 bat "docker-compose up -d"
                 script {
                    //def imageTar = "${env.WORKSPACE}\\${DOCKER_IMAGE}.tar"
                    //bat "docker save -o ${imageTar} ${env.DOCKER_IMAGE}"
                    //bat "move ${imageTar} ${env.SAVE_PATH}"

                    // Save the Docker image to a tar file
                    bat "docker save -o ${env.IMAGE_NAME}-${env.IMAGE_TAG}.tar ${env.IMAGE_NAME}:${env.IMAGE_TAG}"

                    // Copy the tar file to the desired local path
                    bat "move ${env.IMAGE_NAME}-${env.IMAGE_TAG}.tar ${env.SAVE_PATH}"
                  }
            }
        }
    }

    post {
        always {
            // Clean up workspace after the build
            cleanWs()
        }
        success {
            // Actions to take on a successful build
            echo 'Build and deployment successful!'
        }
        failure {
            // Actions to take on a failed build
            echo 'Build or deployment failed.'
        }
    }
}
