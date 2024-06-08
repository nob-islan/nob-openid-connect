-- テーブル作成
CREATE TABLE IF NOT EXISTS authorization_code_info (
    code_id INT NOT NULL AUTO_INCREMENT
    , code_value VARCHAR(32) NOT NULL
    , code_challenge VARCHAR(64) NOT NULL
    , expiration_date_time DATETIME NOT NULL
    , is_deleted BOOLEAN NOT NULL
    , PRIMARY KEY(code_id)
);