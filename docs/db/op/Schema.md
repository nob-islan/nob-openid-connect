# OpenID プロバイダ スキーマ設計

## データベース名

`OPDB`

## テーブル一覧

| テーブル名 | table_name | 説明                 |
| ---------- | ---------- | -------------------- |
| ユーザ情報 | user_info  | ユーザ情報を管理する |

## テーブル定義

### user_info

| カラム名   | column_name | 型          | NULL | PK  | 説明                 |
| ---------- | ----------- | ----------- | ---- | --- | -------------------- |
| ユーザ ID  | user_id     | VARCHAR(10) | x    | o   | ログイン用 ID        |
| パスワード | password    | VARCHAR(32) | x    |     | ログイン用パスワード |
| ユーザ名   | user_name   | VARCHAR(10) | x    |     | 画面表示用ユーザ名   |
