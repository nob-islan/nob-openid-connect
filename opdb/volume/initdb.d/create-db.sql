-- データベース作成
CREATE DATABASE opdb;
USE opdb;

-- テーブル作成 client_info
CREATE TABLE client_info (
    client_id VARCHAR(15) 
    , client_secret VARCHAR(32) NOT NULL
    , redirect_uri TEXT NOT NULL
    , PRIMARY KEY(client_id)
);

-- テーブル作成 user_info
CREATE TABLE IF NOT EXISTS user_info (
    username VARCHAR(10) NOT NULL
    , password VARCHAR(32) NOT NULL
    , PRIMARY KEY(username)
);

-- 初期データ投入 client_info
INSERT INTO client_info (
    client_id
    , client_secret
    , redirect_uri
) VALUES (
    "first-client"
    , "123123123"
    , "http://localhost:8080/auth/token"
);

-- 初期データ投入 user_info
INSERT INTO user_info (
    username
    , password
) VALUES (
    "nob"
    , "password"
);
