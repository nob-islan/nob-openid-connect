# 認可エンドポイントリダイレクト API

```mermaid
%%{init:{'theme':'dark'}}%%
graph TD;
    A(処理開始) --> B[codeVerifier生成、codeChallenge計算]
    B --> |OP| C[認可エンドポイント呼び出し]
    C --> D(処理終了)
```
