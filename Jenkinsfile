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

        stage('Déploiement vers Tomcat 10.1') {
            steps {
                echo '🚀 Déploiement vers le serveur Tomcat 10.1...'
                script {
                    // Trouver le fichier WAR
                    def warFiles = findFiles(glob: '**/*.war')

                    if (files.length == 0) {
                        error "❌ Aucun fichier WAR trouvé pour le déploiement"
                    }

                    def warPath = warFiles[0].path
                    echo "📦 Fichier à déployer: ${warPath}"

                    // Déploiement avec curl
                    withCredentials([usernamePassword(
                        credentialsId: '8219abb7-1b49-476a-b4b8-f82fcee3adda',
                        usernameVariable: 'luca',
                        passwordVariable: 'luca1593'
                    )]) {
                        sh """
                            echo "🔗 Connexion à Tomcat 10.1..."

                            # Vérifier que Tomcat est accessible
                            if curl -s -u $TOMCAT_USER:$TOMCAT_PASS "${TOMCAT_URL}/list" > /dev/null; then
                                echo "✅ Tomcat 10.1 accessible"
                            else
                                echo "❌ Impossible de se connecter à Tomcat"
                                exit 1
                            fi

                            # Arrêter l'application si elle existe déjà
                            echo "🛑 Arrêt de l'application existante..."
                            curl -s -u $TOMCAT_USER:$TOMCAT_PASS \
                                "${TOMCAT_URL}/undeploy?path=/${CONTEXT_PATH}" || echo "⚠️ Aucune application à arrêter"

                            # Déployer la nouvelle version
                            echo "🚀 Déploiement de l'application..."
                            curl -f -u $TOMCAT_USER:$TOMCAT_PASS \
                                -X PUT \
                                --upload-file "${warPath}" \
                                "${TOMCAT_URL}/deploy?path=/${CONTEXT_PATH}&update=true"

                            echo "✅ Déploiement terminé avec succès!"

                            # Vérifier le déploiement
                            echo "🔍 Vérification du déploiement..."
                            sleep 10
                            curl -s -u $TOMCAT_USER:$TOMCAT_PASS \
                                "${TOMCAT_URL}/list" | grep "/${CONTEXT_PATH}:running" && \
                                echo "✅ Application déployée et en cours d'exécution"
                        """
                    }
                }
            }
        }

        stage('Vérification post-déploiement') {
            steps {
                echo '🔍 Vérification du déploiement...'
                script {
                    sh """
                        echo "🌐 URL de l'application: http://localhost:8080/${CONTEXT_PATH}"
                        echo "⏰ Attente du démarrage de l'application..."
                        sleep 30

                        # Test simple de l'application
                        if curl -s -f "http://localhost:8080/${CONTEXT_PATH}" > /dev/null; then
                            echo "✅ Application accessible et fonctionnelle"
                        else
                            echo "⚠️ Application déployée mais non accessible immédiatement"
                        fi
                    """
                }
            }
        }
    }

    post {
        always {
            echo '📊 Pipeline terminé - Nettoyage en cours...'
            // Nettoyage des fichiers temporaires
            sh 'find . -name "pom.xml.backup" -delete || true'
        }

        success {
            echo '🎉 Pipeline exécuté avec succès !'
            script {
                def applicationUrl = "http://localhost:8080/${env.CONTEXT_PATH}"
                emailext (
                    subject: "✅ DÉPLOIEMENT RÉUSSI: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """
                    Le déploiement sur Tomcat 10.1 a été effectué avec succès!

                    Détails:
                    - Application: ${env.APP_NAME}
                    - Version: ${env.VERSION}
                    - Build: #${env.BUILD_NUMBER}
                    - URL: ${applicationUrl}
                    - Consulter: ${env.BUILD_URL}

                    L'application est maintenant disponible sur Tomcat 10.1.
                    """,
                    to: "luca.adam23@gmail.com"
                )
            }
        }

        failure {
            echo '❌ Pipeline échoué !'
            emailext (
                subject: "❌ DÉPLOIEMENT ÉCHOUÉ: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                Le déploiement sur Tomcat 10.1 a échoué.

                Détails:
                - Application: ${env.APP_NAME}
                - Build: #${env.BUILD_NUMBER}
                - Consulter les logs: ${env.BUILD_URL}

                Veuillez vérifier:
                1. Que Tomcat 10.1 est démarré
                2. Les identifiants de déploiement
                3. La configuration de l'application
                """,
                to: "luca.adam23@gmail.com"
            )
        }

        unstable {
            echo '⚠️ Pipeline instable (échec des tests unitaires ?)'
        }
    }
}