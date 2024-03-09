# エンドポイント一覧

## 認証開始 URL

`/authorization`

## 画面 URL

| 画面名                       | URL      |
| ---------------------------- | -------- |
| 認証・ユーザ情報提供同意画面 | `/login` |
| トップ画面                   | `/top`   |

## リライングパーティ API エンドポイント

| API 名                                 | メソッド | URL                              |
| -------------------------------------- | -------- | -------------------------------- |
| 認可エンドポイントリダイレクト         | GET      | `/api/rp/redirect/authorization` |
| トークン発行エンドポイントリダイレクト | GET      | `/api/rp/redirect/token`         |
| トークンリクエスト                     | GET      | `/api/rp/token/issue`            |
| ID トークン検証                        | POST     | `/api/rp/token/verify`           |
| ユーザプロフィールリクエスト           | GET      | `/api/rp/userinfo`               |
| トップ画面リダイレクト                 | GET      | `/api/rp/redirect/top`           |

## OpenID プロバイダ API エンドポイント

| API 名        | メソッド | URL                     |
| ------------- | -------- | ----------------------- |
| 認可          | GET      | `/api/op/authorization` |
| 認証          | POST     | `/api/op/certification` |
| トークン発行  | POST     | `/api/op/token`         |
| UserInfo 取得 | GET      | `/api/op/userinfo`      |
