#!/bin/bash

PROJECT_DIR="../../../../rp-web-project"
CONTAINER_IMAGE="rp-web"

# ビルド
npm --prefix ${PROJECT_DIR} run build

# 成果物を一時ディレクトリにコピー
mkdir -p ./tmp/
cp -r ${PROJECT_DIR}/build/ ./tmp/build/

# コンテナイメージビルド
docker build . -t ${CONTAINER_IMAGE}

# 一時ディレクトリ削除
rm -rf ./tmp
