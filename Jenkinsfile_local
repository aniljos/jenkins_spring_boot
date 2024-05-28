pipeline {
    agent any

    tools {
        // Define the JDK and Maven installations from the Global Tool Configuration
        jdk 'JDK21'
        //maven 'Maven 3.6.3'
    }

    environment {
        // Define any environment variables
        MAVEN_HOME = tool name: 'maven 3.9.6'
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
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
        stage('Package') {
            steps {
                // Package the application (already done in the build stage, but included here for clarity)
                bat 'mvn package'
            }
        }
        stage('Deploy') {
            steps {
                // Deploy the application
                // This step can include copying the artifact to a server, running Docker commands, etc.
                echo 'Deploying application...'
                // Example: Copy the JAR file to a remote server
                // sh 'scp target/your-app.jar user@remote.server:/path/to/deploy'

                script {
                       def sourcePath = "${env.WORKSPACE}\\target\\app-services-1.0.0.jar" // Update with your actual JAR file name
                       def destinationPath = "D:\\Jenkins\\Spring\\builds" // Update with your desired destination path

                       // Copy the built JAR file to the destination path
                       bat "copy ${sourcePath} ${destinationPath}"
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
