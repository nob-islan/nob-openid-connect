# UserInfo 取得 API

```mermaid
%%{init:{'theme':'dark'}}%%
graph TD;
    A(処理開始) --> B[DBにアクセスしてUserInfo取得]
    B --> C[レスポンスを作成、返却]
    B -.-> D[(user_infoテーブル)]
    C --> E(処理終了)
```
