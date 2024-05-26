# ローカル環境でのアプリ起動手順

`local`配下に、ローカルでアプリケーションを起動するための各種設定ファイルを配置しています。このページではアプリ起動のための手順を記載します。

## コンテナイメージ作成

- rp-web

  ```shell
  cd image/rp-web
  bash image-build.sh
  ```

- rp-app

  ```shell
  cd image/rp-app
  bash image-build.sh
  ```

- op-web

  ```shell
  cd image/op-web
  bash image-build.sh
  ```

- op-app

  ```shell
  cd image/op-app
  bash image-build.sh
  ```

## コンテナ起動

```shell
cd local/docker
docker compose up -d
```
