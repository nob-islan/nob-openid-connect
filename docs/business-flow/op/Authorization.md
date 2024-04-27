# 認可 API

```mermaid
%%{init:{'theme':'dark'}}%%
graph TD;
    A(処理開始) --> B[codeChallengeを保持]
    B --> C[認証・ユーザ情報提供同意画面を返却]
    C --> D(処理終了)
```
