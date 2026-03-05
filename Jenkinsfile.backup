pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }
    
    environment {
        APP_NAME = 'product-management-api'
    }
    
    parameters {
        choice(name: 'ENVIRONMENT', choices: ['dev', 'stage', 'prod'], description: 'Deployment environment')
        booleanParam(name: 'RUN_TESTS', defaultValue: true, description: 'Run tests')
        booleanParam(name: 'DEPLOY_APP', defaultValue: true, description: 'Deploy application')
    }
    
    stages {
        stage('📋 Info') {
            steps {
                echo """
                ═══════════════════════════════════════════════
                🚀 Jenkins CI/CD Pipeline Started
                ═══════════════════════════════════════════════
                Build:       #${env.BUILD_NUMBER}
                Environment: ${params.ENVIRONMENT}
                Run Tests:   ${params.RUN_TESTS}
                Deploy:      ${params.DEPLOY_APP}
                ═══════════════════════════════════════════════
                """
            }
        }
        
        stage('1️⃣ Checkout') {
            steps {
                echo '📥 Checking out code from GitHub...'
                checkout scm
                sh 'echo "✅ Code checked out successfully"'
            }
        }
        
        stage('2️⃣ Compile') {
            steps {
                echo '🔨 Compiling Java code...'
                sh 'mvn clean compile'
                echo '✅ Compilation successful'
            }
        }
        
        stage('3️⃣ Test') {
            when {
                expression { params.RUN_TESTS == true }
            }
            steps {
                echo '🧪 Running tests...'
                sh 'mvn test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
                success {
                    echo '✅ All tests passed!'
                }
                failure {
                    echo '❌ Tests failed!'
                }
            }
        }
        
        stage('4️⃣ Package') {
            steps {
                echo '📦 Building JAR file...'
                script {
                    if (params.RUN_TESTS) {
                        sh 'mvn package'
                    } else {
                        sh 'mvn package -DskipTests'
                    }
                }
                sh 'ls -lh target/*.jar'
                echo '✅ Package created'
            }
        }
        
        stage('5️⃣ Archive') {
            steps {
                echo '💾 Archiving artifacts...'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                echo '✅ Artifacts archived'
            }
        }
        
        stage('6️⃣ Deploy') {
            when {
                expression { params.DEPLOY_APP == true }
            }
            steps {
                script {
                    def port = params.ENVIRONMENT == 'dev' ? 8080 :
                               params.ENVIRONMENT == 'stage' ? 8081 : 8082
                    
                    echo "🚀 Deploying to ${params.ENVIRONMENT} (port ${port})"
                    
                    sh """
                        # Stop old app
                        PID=\$(lsof -t -i:${port} 2>/dev/null) || true
                        if [ ! -z "\$PID" ]; then
                            kill -15 \$PID 2>/dev/null || true
                            sleep 3
                        fi
                        
                        # Find JAR
                        JAR_FILE=\$(ls target/*.jar | head -1)
                        
                        # Start app
                        mkdir -p logs
                        nohup java -jar \$JAR_FILE \\
                            --spring.profiles.active=${params.ENVIRONMENT} \\
                            --server.port=${port} \\
                            > logs/app-${params.ENVIRONMENT}.log 2>&1 &
                        
                        echo \$! > logs/app-${params.ENVIRONMENT}.pid
                        echo "✅ Application started on port ${port}"
                    """
                }
            }
        }
        
        stage('7️⃣ Health Check') {
            when {
                expression { params.DEPLOY_APP == true }
            }
            steps {
                script {
                    def port = params.ENVIRONMENT == 'dev' ? 8080 :
                               params.ENVIRONMENT == 'stage' ? 8081 : 8082
                    
                    echo '🏥 Checking application health...'
                    sh """
                        sleep 15
                        
                        if curl -f http://localhost:${port}/actuator/health 2>/dev/null; then
                            echo "✅ Health check PASSED (Actuator)"
                        elif curl -f http://localhost:${port}/products/getdata 2>/dev/null; then
                            echo "✅ Health check PASSED (API)"
                        elif lsof -i:${port} > /dev/null 2>&1; then
                            echo "⚠️  App running but endpoints not ready"
                        else
                            echo "❌ Health check FAILED"
                            exit 1
                        fi
                    """
                }
            }
        }
    }
    
    post {
        success {
            script {
                def port = params.ENVIRONMENT == 'dev' ? 8080 :
                           params.ENVIRONMENT == 'stage' ? 8081 : 8082
                echo """
                
                ✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅
                ✅     PIPELINE COMPLETED SUCCESSFULLY!     ✅
                ✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅
                
                Build:       #${env.BUILD_NUMBER}
                Environment: ${params.ENVIRONMENT}
                Port:        ${port}
                
                🔗 Access:
                http://localhost:${port}/products/getdata
                http://localhost:${port}/actuator/health
                
                """
            }
        }
        failure {
            echo """
            ❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌
            ❌          PIPELINE FAILED!            ❌
            ❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌❌
            """
        }
    }
}
