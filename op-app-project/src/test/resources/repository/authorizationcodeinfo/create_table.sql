-- テーブル作成
CREATE TABLE IF NOT EXISTS authorization_code_info (
    code_id INT NOT NULL AUTO_INCREMENT
    , code_value VARCHAR(32) NOT NULL
    , code_challenge VARCHAR(64) NOT NULL
    , expiration_date_time DATETIME NOT NULL
    , is_deleted BOOLEAN NOT NULL
    , PRIMARY KEY(code_id)
);

-- テストデータ挿入 有効な認可コード
INSERT INTO authorization_code_info (
    code_value
    , code_challenge
    , expiration_date_time
    , is_deleted
) VALUES (
    'testCodeValue1'
    , 'testCodeChallenge1'
    , '2124-06-09 00:00:00.000'
    , 0
);

-- テストデータ挿入 期限が切れている認可コード
INSERT INTO authorization_code_info (
    code_value
    , code_challenge
    , expiration_date_time
    , is_deleted
) VALUES (
    'testCodeValue2'
    , 'testCodeChallenge2'
    , '2014-06-09 00:00:00.000'
    , 0
);

-- テストデータ挿入 削除済みのコード
INSERT INTO authorization_code_info (
    code_value
    , code_challenge
    , expiration_date_time
    , is_deleted
) VALUES (
    'testCodeValue3'
    , 'testCodeChallenge3'
    , '2124-06-09 00:00:00.000'
    , 1
);