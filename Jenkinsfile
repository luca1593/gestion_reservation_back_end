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
                sh 'mvn clean compile'
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
                sh 'mvn package'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
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

                    if (fileExists('scripts/start-app.sh')) {
                        sh """
                            chmod +x scripts/start-app.sh
                            scripts/start-app.sh ${fullJarPath} ${BUILD_ID}
                        """
                    } else {
                        echo '⚠️ Script de démarrage non trouvé, exécution directe du JAR...'
                        sh "nohup java -jar ${fullJarPath} --server.port=8081 &"
                    }
                }
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