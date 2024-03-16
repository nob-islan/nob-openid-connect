-- テーブル作成
CREATE TABLE IF NOT EXISTS user_info (
    user_id INT AUTO_INCREMENT PRIMARY KEY
    , login_id VARCHAR(10) NOT NULL
    , password VARCHAR(32) NOT NULL
    , user_name VARCHAR(20) NOT NULL
);

-- テストデータ挿入
INSERT INTO user_info (
    login_id
    , password
    , user_name
) VALUES (
    'nob'
    , 'p@ssw0rd'
    , 'nobuhiro'
);