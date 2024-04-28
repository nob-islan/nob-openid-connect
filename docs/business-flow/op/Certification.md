# 認証 API

```mermaid
%%{init:{'theme':'dark'}}%%
graph TD;
    A(処理開始) --> B[ユーザID, パスワード検証]
    B --> C[認可コードを発行]
    C --> |RP| D[アクセストークン取得APIへリダイレクト]
    D --> E(処理終了)
```
