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
                             echo "Aucune instance existante détectée."
                         fi
                     """

                     // Lancer la nouvelle instance en arrière-plan
                     echo "Lancement de la nouvelle instance..."
                     sh "nohup java -jar ${jarFile} > app.log 2>&1 &"
                     echo "Application démarrée. Logs : app.log"

                     // Vérification du port 8080 avec protection
                     echo "Vérification du démarrage de l'application..."
                     def responseCode = sh(
                         script: '''
                             sleep 5
                             curl -s -o /dev/null -w '%{http_code}' http://localhost:8080 || echo "000"
                         ''',
                         returnStdout: true
                     ).trim()

                     if (responseCode == '200') {
                         echo "Application accessible (HTTP 200)"
                     } else {
                         echo "Application non accessible (HTTP ${responseCode})"
                     }

                 } else {
                     echo "Aucun fichier JAR trouvé dans target/"
                 }
             }
         }
     }
}