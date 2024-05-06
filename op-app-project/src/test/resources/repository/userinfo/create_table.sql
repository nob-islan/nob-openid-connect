-- テーブル作成
CREATE TABLE IF NOT EXISTS user_info (
    user_id VARCHAR(10) NOT NULL
    , password VARCHAR(32) NOT NULL
    , PRIMARY KEY(user_id)
);

-- テストデータ挿入
INSERT INTO user_info (
    user_id
    , password
) VALUES (
    'nob'
    , 'p@ssw0rd'
);