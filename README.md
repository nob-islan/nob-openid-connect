# Nob OpenID Connect

標準的な OpenID Connect の**認可コードフロー**に従った認証機能をフルスクラッチで実装するプロジェクトです。

## 設計書一覧

- [業務シーケンス](./docs/Business-sequence.md)
- [業務フロー](./docs/business-flow/README.md)
- [データベース定義](./docs/db/README.md)
- [エンドポイント](./docs/endpoint/README.md)
- [API 定義](./docs/api/README.md)
- [画面 URL](./docs/screen/README.md)

## WIP: コンポーネント一覧

インターフェース定義については[swagger](http://localhost:8080/swagger-ui/index.html)を確認してください。

- リライングパーティ
- OpenID プロバイダ

## メモ

認証開始: `localhost:8080/api/rp/redirect/authorization`
