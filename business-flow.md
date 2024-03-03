# Nob OpenID Connect 設計書

標準的な OpenID Connect に従った認証機能をフルスクラッチで実装する用の設計書です。`response_type=code`であり、 `scope`に`openid`を含む場合

## 業務フロー

業務シーケンスの概要です。

```mermaid
%%{init:{'theme':'dark'}}%%
sequenceDiagram
autonumber
    actor user as ユーザ
    participant relying_party as リライングパーティ
    participant id_provider as IDプロバイダ
    participant user_info as UserInfoエンド

    user->>relying_party: OIDCスタート
    relying_party-->>+user: リダイレクト
    user->>id_provider: <br>
    Note over id_provider: 認可エンドポイント
    id_provider-->>user: 認証画面
    user->>id_provider: 認証情報入力・ユーザ認証
    id_provider-->>user: ユーザ情報提供の確認画面
    user->>id_provider: 提供への同意
    id_provider-->>+user: 認可コード発行・リダイレクト
    user->>-relying_party: <br>
    Note over relying_party: トークン発行エンドポイント
    relying_party->>id_provider: トークンリクエスト
    id_provider-->>relying_party: アクセストークン、IDトークン発行
    relying_party->>relying_party: IDトークン検証
    relying_party->>user_info: ユーザプロフィール要求
    user_info-->>relying_party: ユーザプロフィール返却
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
