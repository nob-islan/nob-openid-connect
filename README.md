# Nob OpenID Connect

標準的な OpenID Connect の**認可コードフロー**に従った認証機能をフルスクラッチで実装するプロジェクトです。

## 設計書一覧

- [業務シーケンス](./docs/business-sequence/README.md)
- [業務フロー](./docs/business-flow/README.md)
- [データベース定義](./docs/db/README.md)
- [エンドポイント](./docs/endpoint/README.md)
- [API 定義](./docs/api/README.md)
- [画面 URL](./docs/screen/README.md)

<!-- TODO docs/endpoint は swagger へ移行 -->

## コンポーネント一覧

インターフェース定義については[swagger](http://localhost:8080/swagger-ui/index.html)を確認してください。

- リライングパーティ
- OpenID プロバイダ

### 実装メモ

コーディング規則を記載します。

#### Java パッケージ構成

主要なパッケージについての説明です。

##### config

アプリケーション起動時に読み込まれるコンフィグクラスを配置します。

##### constants

定数クラスを配置します。

##### controller

画面と API とを仲立ちするクラスを配置します。この層では基本的に業務処理は行わず、下記の処理のみ行うようにします:

- 画面からのリクエストを API 向けに変換
- API からのレスポンスを画面に表示する形に変換

##### dto

データ格納オブジェクトを行うクラスを配置します。各種 dto について、それがどの層に属するかによって下記で命名します:

| 層         | 命名（入力 / 出力）           |
| ---------- | ----------------------------- |
| controller | `XXXRequest` / `XXXResponse`  |
| service    | `XXXInModel` / `XXXOutModel`  |
| repository | `XXXKey` / `{エンティティ名}` |

##### entity

データベースのテーブルに対応するエンティティクラスを配置します。

##### mapper

SQL を Java インターフェースにマッピングするマッパークラスを配置します。

##### repository

データベースにアクセスするクラスを配置します。

##### service

業務処理を行うクラスを配置します。

#### React パッケージ構成

主要なパッケージについての説明です。

##### actions

各種アクションを配置します。主に下記 2 つの処理を任せます:

- state の状態を更新するために reducer を呼び出す
- API をコールする

##### components

画面のコンテンツとなるクラスを配置します。

##### constants

共通して利用する定数クラスを配置します。

##### reducers

state を更新するクラスを配置します。

##### states

画面の状態を管理するクラスを配置します。

## 開発メモ

認証開始: `localhost:8080/api/rp/authorization?redirectUri=http%3A%2F%2Flocalhost%3A3000%2Fredirect-fetchtoken`

## 参考文献

- [OpenID Connect について勉強したのでまとめる](https://zenn.dev/bonvoyage/articles/5dda6a1effd022)
- [30 分で OpenID Connect 完全に理解したと言えるようになる勉強会](https://speakerdeck.com/d_endo/30fen-deopenid-connectwan-quan-nili-jie-sitatoyan-eruyouninarumian-qiang-hui?slide=65)
- [図解 OpenID Connect による ID 連携](https://qiita.com/TakahikoKawasaki/items/701e093b527d826fd62c)
- [OpenID Connect 全フロー解説](https://qiita.com/TakahikoKawasaki/items/4ee9b55db9f7ef352b47#1-response_typecode)
- [OpenID Connect 概要](https://www.slideshare.net/oidfj/openid-connect-intro-september-2013)
- [OpenID Connect の定義をわかりやすく解説](https://qiita.com/rendaman0215/items/94ade32a5e38c47ec5b4)
- [OAuth 2.0 認可コードフロー](https://qiita.com/TakahikoKawasaki/items/200951e5b5929f840a1f#1-%E8%AA%8D%E5%8F%AF%E3%82%B3%E3%83%BC%E3%83%89%E3%83%95%E3%83%AD%E3%83%BC)
- [OIDC Basics](https://www.authlete.com/ja/developers/tutorial/oidc/)
