pipeline{
    agent any

    tools{
        maven "Maven"
    }

    environment {
        // Variables d'environnement
        APP_NAME = 'gsrt-app'
        VERSION = '1.0.0'
        BUILD_ID = "${currentBuild.number}"
        WORKSPACE_DIR = "${WORKSPACE}"
    }

    stages {
        stage('Compilation') {
            steps {
                echo "Compilation du projet..."
                sh 'mvn clean compile -DskipTests=true'
            }
        }
        stage('Tests unitaires') {
            steps {
                echo "Exécution des tests..."
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml' // Publication des résultats des tests
                }
            }
        }
        stage('Packaging') {
            steps {
                echo "Packaging du projet..."
                sh 'mvn package  -DskipTests=true'
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
                     echo "JAR trouvé: ${fullJarPath}"
                     // Exécution du script interne (exemple)
                     if (fileExists('scripts/start-app.sh')) {
                        sh """
                            chmod +x scripts/start-app.sh
                            scripts/start-app.sh ${fullJarPath} ${BUILD_ID}
                        """
                     } else {
                        // Fallback: exécution directe si le script n'existe pas
                        sh "java -jar ${fullJarPath} --server.port=8080 &"
                        echo '⚠️ Script interne non trouvé, exécution directe du JAR'
                     }
                }
            }
        }
    }
    post {
        always {
            echo '📊 Pipeline terminé - Nettoyage en cours...'
                 // Nettoyage si nécessaire
            }
            success {
                echo '🎉 Pipeline exécuté avec succès!'
                // Notification de succès
            }
            failure {
                echo '❌ Pipeline échoué!'
                // Notification d'échec
                emailext (
                    subject: "❌ BUILD FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: "La construction a échoué. Consultez: ${env.BUILD_URL}",
                    to: "luca.adam23@gmail.com"
                )
            }
            unstable {
                echo '⚠️ Pipeline instable (tests échoués)'
            }
        }

        options {
            timeout(time: 30, unit: 'MINUTES') // Timeout global
            buildDiscarder(logRotator(numToKeepStr: '10')) // Garder seulement les 10 derniers builds
        }
    }
}