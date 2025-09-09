pipeline{
    agent any

    tools{
        maven "LOCAL_MAVEN"
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
                sh 'java -jar target/*.jar'
            }
        }
    }

}