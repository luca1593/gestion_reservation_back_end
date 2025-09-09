#!/bin/bash
# Script pour démarrer l'application Java
JAR_FILE=$1
BUILD_ID=$2
PORT=8081
LOG_DIR="/var/log/gsrt"
APP_NAME="gsrt"

echo "Démarrage de l'application ${APP_NAME} (Build: ${BUILD_ID})"

# Vérification que le JAR existe
if [ ! -f "$JAR_FILE" ]; then
    echo "❌ Fichier JAR introuvable: $JAR_FILE"
    exit 1
fi

# Création du répertoire de logs si nécessaire
mkdir -p $LOG_DIR

# Arrêt de l'instance précédente si elle tourne
echo "🛑 Arrêt de l'instance précédente..."
pkill -f "java -jar.*$JAR_FILE" || true
sleep 2

# Démarrage de la nouvelle instance
echo "✅ Démarrage de la nouvelle instance..."
nohup java -jar $JAR_FILE \
    --server.port=$PORT \
    --spring.profiles.active=prod \
    > $LOG_DIR/app_$BUILD_ID.log 2>&1 &

# Attente du démarrage
sleep 5

# Vérification que l'application tourne
if pgrep -f "java -jar.*$JAR_FILE" > /dev/null; then
    echo "✅ Application démarrée avec succès!"
    echo "📊 Logs: $LOG_DIR/app_$BUILD_ID.log"
    echo "🌐 URL: http://localhost:$PORT"
else
    echo "❌ Échec du démarrage de l'application"
    echo "📋 Dernières lignes des logs:"
    tail -20 $LOG_DIR/app_$BUILD_ID.log
    exit 1
fi