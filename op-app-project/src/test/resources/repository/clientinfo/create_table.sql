-- テーブル作成
CREATE TABLE IF NOT EXISTS client_info (
    client_id VARCHAR(10) NOT NULL
    , client_secret VARCHAR(32) NOT NULL
    , redirect_uri TEXT NOT NULL
    , PRIMARY KEY(client_id)
);

-- テストデータ挿入
INSERT INTO client_info (
    client_id
    , client_secret
    , redirect_uri
) VALUES (
    'testClient'
    , 'testClient'
    , 'http://example.nob/sample'
);