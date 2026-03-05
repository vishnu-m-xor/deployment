pipeline {
    agent any
    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo 'Code checked out successfully'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
      stage('Deploy') {
    steps {
        sh '''
            JAR_FILE=$(ls target/*.jar | grep -v original | head -1)

            # Kill the process on port 8081 (works without sudo)
            fuser -k 8081/tcp || true
            sleep 3

            nohup java -jar $JAR_FILE --server.port=8081 > app.log 2>&1 &
            echo "App started on port 8081"
        '''
    }
}
        stage('Health Check') {
            steps {
                sh '''
                    echo "Waiting for app to start..."
                    sleep 15
                    STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8081/products/getdata)
                    if [ "$STATUS" = "200" ]; then
                        echo "✅ App is running! HTTP $STATUS"
                    else
                        echo "❌ App failed to start. HTTP $STATUS"
                        cat app.log
                        exit 1
                    fi
                '''
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed — check the logs.'
        }
    }
}
