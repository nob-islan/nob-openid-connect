# エンドポイント一覧

各種画面 URL および API のエンドポイントを定義します。

## 認証開始用 URL

`/authorization`

### 画面 URL

| 画面名                       | URL      |
| ---------------------------- | -------- |
| 認証・ユーザ情報提供同意画面 | `/login` |
| トップ画面                   | `/top`   |

## リライングパーティ API エンドポイント

| API 名                         | メソッド | URL                   |
| ------------------------------ | -------- | --------------------- |
| 認可エンドポイントリダイレクト | GET      | `/api/redirect/login` |
| トークンリクエスト             | GET      | `/api/token/issue`    |
| ID トークン検証                | POST     | `/api/token/verify`   |
| ユーザプロフィールリクエスト   | GET      | `/api/userinfo`       |
| トップ画面リダイレクト         | GET      | `/api/redirect/top`   |

## ID プロバイダ API エンドポイント

| API 名        | メソッド | URL                  |
| ------------- | -------- | -------------------- |
| 認可          | GET      | `/api/authorization` |
| 認証          | POST     | `/api/certification` |
| トークン発行  | POST     | `/api/token`         |
| UserInfo 取得 | GET      | `/api/userinfo`      |
