# UserInfo 取得 API

```mermaid
%%{init:{'theme':'dark'}}%%
graph TD;
    A(処理開始) --> B[DBにアクセスしてUserInfo取得]
    B --> C[返却値を作成]
    B -.-> D[(user_infoテーブル)]
    C --> E(処理終了)
```
