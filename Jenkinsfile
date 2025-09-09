pipeline{
    agent any

    tools{
        maven "Maven"
    }

    stages {

        stage('Compilation') {
            steps {
                echo "Compilation du projet..."
                sh 'mvn clean compile'
            }
        }

        stage('Tests unitaires') {
            steps {
                echo "Exécution des tests..."
                sh 'mvn test'
            }
        }

        stage('Packaging') {
            steps {
                echo "Packaging du projet..."
                sh 'mvn package'
            }
        }

        stage('Exécution') {
            steps {
                echo "Lancement de l'application..."
                //sh 'java -jar target/*.jar'
            }
        }
    }
     post {
        success {
            echo "Build réussi. Lancement de l'application..."
            // Trouve le JAR automatiquement et le lance
            script {
            def jarFile = sh(
                script: "find target -name '*.jar' | head -n 1",
                returnStdout: true
                ).trim()
                if (jarFile) {
                    echo "Fichier JAR trouvé : ${jarFile}"
                    // Tuer l'ancienne instance si elle existe
                    echo "Recherche et arrêt de l'ancienne instance..."
                    sh """
                        PID=\$(pgrep -f "java -jar ${jarFile}")
                        if [ ! -z "\$PID" ]; then
                            echo "Ancienne instance trouvée (PID=\$PID), arrêt..."
                            kill -9 \$PID
                        else
                            cho "Aucune instance existante détectée."
                        fi
                    """
                    // Lancer la nouvelle instance en arrière-plan
                    echo "Lancement de la nouvelle instance..."
                    sh "nohup java -jar ${jarFile} > app.log 2>&1 &"
                    echo "Application démarrée. Logs : app.log"
                } else {
                    error "Aucun fichier JAR trouvé dans target/"
                }
            }
        }
     }
}