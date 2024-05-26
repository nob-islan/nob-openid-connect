#!/bin/bash

PROJECT_DIR="../../../../rp-app-project"
JAR_FILE="rp-app-project-0.0.1-SNAPSHOT.jar"
CONTAINER_IMAGE="rp-app"

# jarファイルをビルド
${PROJECT_DIR}/mvnw package -f ${PROJECT_DIR}/pom.xml

# jarファイルを一時ディレクトリにコピー
mkdir -p ./tmp
cp ${PROJECT_DIR}/target/${JAR_FILE} ./tmp

# コンテナイメージビルド
docker build . -t ${CONTAINER_IMAGE}

# 一時ディレクトリ削除
rm -rf ./tmp
