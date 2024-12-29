# はじめての OpenId Connect

**OpenID Connect** のうち、**認可コードフロー**と呼ばれる認証方式でのログイン認証フローについて解説します。

## 登場人物

### リライングパーティ（RP）

業務サービスの提供を担当します。認可・認証を後述の OpenID プロバイダに一任しているため、リライングパーティのサーバ内にはユーザ個人を特定できる情報を持たずに済みます。

### OpenID プロバイダ（OP）

認証を担当します。OpenID プロバイダが発行したアクセストークンを使うことでユーザがリライングパーティのサービスを利用することができるようになります。

## 認証フロー

認証開始から終了までのフローです。

```mermaid
%%{init:{'theme':'dark'}}%%
sequenceDiagram
autonumber
    actor user as ユーザ
    participant rp as リライングパーティ
    participant op as OpenIDプロバイダ

    user->>rp: OIDC認証開始
    rp-->>+user: リダイレクト
    user->>-op: 認可要求
    op->>op: 認可リクエスト検証
    op-->>user: 認証画面
    user->>op: 認証要求
    op->>op: 認証リクエスト検証
    op-->>+user: リダイレクト
    user->>-rp: <br>
    rp->>op: トークン発行要求
    op->>op: トークンリクエスト検証
    op-->>rp: アクセストークン、IDトークン
    rp->>rp: IDトークン検証
    rp-->>+user: リダイレクト
    user->>-rp: <br>
    rp-->>user: ログイン完了画面
```

## 各種処理詳細

WIP
