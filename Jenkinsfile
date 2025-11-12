pipeline {
    agent any

    tools {
        maven "Maven"
    }

    environment {
        APP_NAME = "grstapp"
        DOCKER_COMPOSE_FILE = "docker-compose.yml"
    }

    stages {

        stage('Compilation') {
            steps {
                echo "🧩 Compilation du projet..."
                sh 'mvn clean compile'
            }
        }

        stage('Packaging') {
            steps {
                echo "📦 Packaging du projet..."
                sh 'mvn package -DskipTests'
            }
        }

        stage('Construction de l’image Docker') {
            steps {
                echo "🐳 Construction de l’image Docker de l’application..."
                sh "docker build -t ${APP_NAME}:latest ."
            }
        }

        stage('Déploiement avec Docker Compose') {
            steps {
                echo "🚀 Lancement des containers avec Docker Compose..."
                // On arrête les anciens containers s’ils tournent
                sh "docker compose -f ${DOCKER_COMPOSE_FILE} down || true"
                // On reconstruit et relance les services
                sh "docker compose -f ${DOCKER_COMPOSE_FILE} up -d --build"
            }
        }

        stage('Vérification du déploiement') {
            steps {
                echo "🔍 Vérification du déploiement..."
                sh "docker ps"
                sh "docker logs grstapp --tail=50 || true"
            }
        }
    }

    post {
        success {
            echo "✅ Déploiement réussi ! L’application est accessible sur http://localhost:8084"
        }
        failure {
            echo "❌ Le pipeline a échoué. Vérifie les logs Jenkins et Docker."
        }
    }
}
