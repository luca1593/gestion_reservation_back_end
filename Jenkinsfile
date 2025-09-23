pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        // Variables d'environnement
        APP_NAME = 'gsrt-app'
        VERSION = '1.0.0'
        BUILD_ID = "${currentBuild.number}"
        WORKSPACE_DIR = "${WORKSPACE}"
    }

    options {
        timeout(time: 30, unit: 'MINUTES')                      // Timeout global
        buildDiscarder(logRotator(numToKeepStr: '10'))          // Conserver les 10 derniers builds
    }

    stages {
        stage('Compilation') {
            steps {
                echo '📦 Compilation du projet...'
                sh 'mvn clean compile  -DskipTests=true'
            }
        }

        stage('Tests unitaires') {
            steps {
                echo '🧪 Exécution des tests unitaires...'
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'    // Publication des résultats de test
                }
            }
        }

        stage('Packaging') {
            steps {
                echo '📦 Packaging du projet...'
                sh 'mvn package  -DskipTests=true'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.war', fingerprint: true
                    echo '✅ Package créé avec succès'
                }
            }
        }

        stage('Déploiement & Exécution') {
            steps {
                script {
                    echo '🚀 Lancement de l\'application...'
                    // Trouver le JAR généré
                    def jarFile = findFiles(glob: 'target/*.jar')[0].name
                    def fullJarPath = "${WORKSPACE}/target/${jarFile}"
                    echo "🗂️ JAR trouvé: ${fullJarPath}"

                    writeFile file: 'scripts/app-params.env', text: """
                    JAR_FILE=${fullJarPath}
                    BUILD_ID=${BUILD_ID}
                    """.stripIndent()

                }
                deploy adapters: [tomcat10(alternativeDeploymentContext: '', credentialsId: '2b628e71-79b5-4c97-9e98-22b6bc8d839c', path: '', url: 'http://12.24.5.100:8080/')], contextPath: 'gsrt', war: '**/*.war'
            }
        }
    }

    post {
        always {
            echo '📊 Pipeline terminé - Nettoyage en cours...'
            // Actions de nettoyage, si nécessaire
        }

        success {
            echo '🎉 Pipeline exécuté avec succès !'
            // Notification ou actions supplémentaires
        }

        failure {
            echo '❌ Pipeline échoué !'
            // Envoi d’un email en cas d’échec
            emailext (
                subject: "❌ BUILD FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "La construction a échoué. Consultez: ${env.BUILD_URL}",
                to: "luca.adam23@gmail.com"
            )
        }

        unstable {
            echo '⚠️ Pipeline instable (échec de tests/unitaires)'
        }
    }
}


pipeline{
    agent any
    tools{
        maven "LOCAL_MAVEN"
    }
    stages{
        stage('Build'){
            steps{
                sh 'mvn clean package'
            }
            post{
                success{
                    echo "Archiving the artifacts"
                archiveArtifacts artifacts: "**/target/*.war"
                }
            }
        }
        stage('Deploy To TomCatServer'){
            steps{
                deploy adapters: [tomcat9(credentialsId: '8219abb7-1b49-476a-b4b8-f82fcee3adda', path: '', url: 'http://localhost:8080/')], contextPath: null, war: '**/*.war'
            }
        }
    }
}
