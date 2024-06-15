# トークン発行 API

```mermaid
%%{init:{'theme':'dark'}}%%
graph TD;
    A(処理開始) --> B[認可コード、codeVerifierを検証]
    B --> C[アクセストークン、IDトークン作成]
    C --> D[レスポンスを作成、返却]
    D --> E(処理終了)
```
