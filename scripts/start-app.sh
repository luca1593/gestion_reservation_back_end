#!/bin/bash
# Script pour démarrer l'application Java sans génération ou lecture de logs

# Lecture des paramètres
source /tmp/app-params.env
PORT=8081
APP_NAME="gsrt"

echo "🚀 Démarrage de l'application ${APP_NAME} (Build: ${BUILD_ID})"

# Vérification que le JAR existe
if [ ! -f "$JAR_FILE" ]; then
    echo "❌ Fichier JAR introuvable: $JAR_FILE"
    exit 1
fi

# Démarrage de la nouvelle instance sans log
echo "✅ Démarrage de la nouvelle instance..."
nohup java -jar "$JAR_FILE" \
    --server.port=$PORT \
    --spring.profiles.active=prod \
    >/dev/null 2>&1 &

# Attente du démarrage
sleep 5

if pgrep -f "java -jar.*$JAR_FILE" > /dev/null; then
    # Récupération de l'adresse IP locale (IPv4 non-loopback)
    SERVER_IP=$(hostname -I | awk '{print $1}')
    echo "✅ Application démarrée avec succès !"
    echo "🌐 URL: http://${SERVER_IP}:${PORT}"
else
    echo "❌ Échec du démarrage de l'application"
    exit 1
fi