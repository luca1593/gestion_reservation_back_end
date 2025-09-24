pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        APP_NAME = 'gsrt-app'
        VERSION = '1.0.0'
        BUILD_ID = "${currentBuild.number}"
        WORKSPACE_DIR = "${WORKSPACE}"
        TOMCAT_URL = 'http://localhost:8080/manager/text'
        CONTEXT_PATH = 'gsrt'
    }

    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
        stage('Compilation') {
            steps {
                echo '📦 Compilation du projet...'
                sh 'mvn clean compile -DskipTests=true'
            }
        }

        stage('Tests unitaires') {
            steps {
                echo '🧪 Exécution des tests unitaires...'
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Packaging') {
            steps {
                echo '📦 Packaging du projet...'
                sh 'mvn package -DskipTests=true'
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
                    echo '✅ Package créé avec succès'
                }
            }
        }

        stage('Déploiement vers Tomcat') {
            steps {
                echo '🚀 Déploiement vers le serveur Tomcat...'
                deploy adapters: [tomcat9(credentialsId: '2b628e71-79b5-4c97-9e98-22b6bc8d839c', path: '', url: 'http://localhost:8080/')], contextPath: null, war: '**/*.war'
            }
        }
    }

    post {
        always {
            echo '📊 Pipeline terminé - Nettoyage en cours...'
        }

        success {
            echo '🎉 Pipeline exécuté avec succès !'
        }

        failure {
            echo '❌ Pipeline échoué !'
            emailext (
                subject: "❌ BUILD FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "La construction a échoué. Consultez: ${env.BUILD_URL}",
                to: "luca.adam23@gmail.com"
            )
        }

        unstable {
            echo '⚠️ Pipeline instable (échec des tests unitaires ?)'
        }
    }
}