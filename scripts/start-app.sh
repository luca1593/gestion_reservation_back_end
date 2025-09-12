#!/bin/bash
# Script pour démarrer l'application Java sans génération ou lecture de logs

JAR_FILE=$1
BUILD_ID=$2
PORT=8081
APP_NAME="gsrt"

echo "🚀 Démarrage de l'application ${APP_NAME} (Build: ${BUILD_ID})"

# Vérification que le JAR existe
if [ ! -f "$JAR_FILE" ]; then
    echo "❌ Fichier JAR introuvable: $JAR_FILE"
    exit 1
fi

# Arrêt de l'instance précédente si elle tourne
echo "🛑 Arrêt de l'instance précédente..."
pkill -f "java -jar.*$JAR_FILE" || true
sleep 2

# Démarrage de la nouvelle instance sans log
echo "✅ Démarrage de la nouvelle instance..."
nohup java -jar "$JAR_FILE" \
    --server.port=$PORT \
    --spring.profiles.active=prod \
    >/dev/null 2>&1 &

# Attente du démarrage
sleep 5

# Vérification que l'application tourne
if pgrep -f "java -jar.*$JAR_FILE" > /dev/null; then
    echo "✅ Application démarrée avec succès !"
    echo "🌐 URL: http://localhost:$PORT"
else
    echo "❌ Échec du démarrage de l'application"
    exit 1
fi