# 業務シーケンス

業務の流れをまとめた仕様書です。

## シーケンス図

業務シーケンスの概要です。

```mermaid
%%{init:{'theme':'dark'}}%%
sequenceDiagram
autonumber
    actor user as ユーザ
    participant relying_party as リライングパーティ
    participant id_provider as IDプロバイダ

    user->>relying_party: OIDCスタート
    Note over relying_party: リダイレクトURI
    relying_party-->>+user: リダイレクト
    user->>id_provider: <br>
    Note over id_provider: 認可エンドポイント
    id_provider-->>user: 認証・ユーザ情報提供同意画面
    user->>id_provider: 認証リクエスト
    Note over id_provider: 認証エンドポイント
    id_provider-->>+user: 認可コード発行・リダイレクト
    user->>-relying_party: <br>
    Note over relying_party: リダイレクトURI
    relying_party->>id_provider: トークンリクエスト
    Note over id_provider: トークン発行エンドポイント
    id_provider-->>relying_party: アクセストークン、IDトークン発行
    relying_party->>relying_party: IDトークン検証
    relying_party->>id_provider: ユーザプロフィールリクエスト
    Note over id_provider: UserInfoエンドポイント
    id_provider-->>relying_party: ユーザプロフィール返却
    relying_party-->>+user: リダイレクト
    user->>-relying_party: <br>
    Note over relying_party: トップ画面
    relying_party-->>+user: トップ画面
```

## 参考文献

- [OpenID Connect について勉強したのでまとめる](https://zenn.dev/bonvoyage/articles/5dda6a1effd022)
- [30 分で OpenID Connect 完全に理解したと言えるようになる勉強会](https://speakerdeck.com/d_endo/30fen-deopenid-connectwan-quan-nili-jie-sitatoyan-eruyouninarumian-qiang-hui?slide=65)
- [図解 OpenID Connect による ID 連携](https://qiita.com/TakahikoKawasaki/items/701e093b527d826fd62c)
- [OpenID Connect 全フロー解説](https://qiita.com/TakahikoKawasaki/items/4ee9b55db9f7ef352b47#1-response_typecode)
- [OpenID Connect 概要](https://www.slideshare.net/oidfj/openid-connect-intro-september-2013)
- [OpenID Connect の定義をわかりやすく解説](https://qiita.com/rendaman0215/items/94ade32a5e38c47ec5b4)
- [OAuth 2.0 認可コードフロー](https://qiita.com/TakahikoKawasaki/items/200951e5b5929f840a1f#1-%E8%AA%8D%E5%8F%AF%E3%82%B3%E3%83%BC%E3%83%89%E3%83%95%E3%83%AD%E3%83%BC)
