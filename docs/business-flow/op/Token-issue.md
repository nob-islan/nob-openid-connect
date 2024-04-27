# トークン発行 API

```mermaid
%%{init:{'theme':'dark'}}%%
graph TD;
    A(処理開始) --> B[認可コード、codeVerifierを検証]
    B --> C[アクセストークン、リフレッシュトークン、IDトークン返却]
    C --> D(処理終了)
```
