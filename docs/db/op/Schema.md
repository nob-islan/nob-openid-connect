# OpenID プロバイダ スキーマ設計

## データベース名

`OPDB`

## テーブル一覧

| テーブル名       | table_name              | 説明                                   |
| ---------------- | ----------------------- | -------------------------------------- |
| ユーザ情報       | user_info               | ユーザ情報を管理します。               |
| クライアント情報 | client_info             | リライングパーティの情報を管理します。 |
| 認可コード情報   | authorization_code_info | 認可コードに紐づく情報を管理します。   |

## テーブル定義

### user_info

| カラム名   | column_name | 型          | NULL | PK  | 説明                 |
| ---------- | ----------- | ----------- | ---- | --- | -------------------- |
| ユーザ ID  | user_id     | VARCHAR(10) | x    | o   | ログイン用 ID        |
| パスワード | password    | VARCHAR(32) | x    |     | ログイン用パスワード |

### client_info

| カラム名                 | column_name   | 型          | NULL | PK  | 説明                                               |
| ------------------------ | ------------- | ----------- | ---- | --- | -------------------------------------------------- |
| クライアント ID          | client_id     | VARCHAR(10) | x    | o   | クライアントを識別する ID                          |
| クライアントシークレット | client_secret | VARCHAR(32) | x    |     | クライアントの認証に用いる文字列                   |
| リダイレクト URI         | redirect_uri  | TEXT        | x    |     | 認可コード発行後のリダイレクト先の検証に用いる URI |

### authorization_code_info

| カラム名       | column_name          | 型          | NULL | PK  | 説明                      |
| -------------- | -------------------- | ----------- | ---- | --- | ------------------------- |
| コード ID      | code_id              | INT         | x    | o   | 自動採番のコード管理用 ID |
| コード値       | code_value           | VARCHAR(32) | x    |     | 認可コードの値            |
| code challenge | code_challenge       | VARCHAR(64) | x    |     | code challenge の値       |
| 有効期限       | expiration_date_time | DATETIME    | x    |     | 認可コードの有効期限      |
| 削除フラグ     | is_deleted           | BOOLEAN     | x    |     | 論理削除のフラグ          |
