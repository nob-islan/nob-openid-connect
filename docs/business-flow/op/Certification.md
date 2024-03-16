# 認証 API

```mermaid
%%{init:{'theme':'dark'}}%%
graph TD;
    A(処理開始) --> B[認可コードを発行]
    B --> |RP| C[トークンリクエストAPIへリダイレクト]
    C --> D(処理終了)
```
