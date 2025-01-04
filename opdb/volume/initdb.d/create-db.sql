-- データベース作成
CREATE DATABASE opdb;
USE opdb;

-- テーブル作成 client_info
CREATE TABLE IF NOT EXISTS client_info (
    client_id VARCHAR(15) NOT NULL
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

-- テーブル作成 authorization_info
CREATE TABLE IF NOT EXISTS authorization_info (
    authorization_id INT NOT NULL AUTO_INCREMENT
    , code VARCHAR(30) NOT NULL
    , username VARCHAR(10) NOT NULL
    , expiration_date_time DATETIME NOT NULL
    , is_deleted BOOLEAN NOT NULL
    , PRIMARY KEY(authorization_id)
);

-- テーブル作成 token_management
CREATE TABLE IF NOT EXISTS token_management (
    client_id VARCHAR(15) NOT NULL
    , hmac_key VARCHAR(30)
    , PRIMARY KEY(client_id)
);

-- テーブル作成 code_challenge_info
CREATE TABLE IF NOT EXISTS code_challenge_info (
    code_challenge_id INT NOT NULL AUTO_INCREMENT
    , code_challenge VARCHAR(128) NOT NULL
    , code_challenge_method VARCHAR(10) NOT NULL
    , is_deleted BOOLEAN NOT NULL
    , PRIMARY KEY(code_challenge_id)
);

-- 初期データ投入 client_info
INSERT INTO client_info (
    client_id
    , client_secret
    , redirect_uri
) VALUES (
    "first-client"
    , "123123123"
    , "http://localhost:8080/login/token"
);

-- 初期データ投入 user_info
INSERT INTO user_info (
    username
    , password
) VALUES (
    "nob"
    , "password"
);

-- 初期データ投入 token_management
INSERT INTO token_management (
    client_id
    , hmac_key
) VALUES (
    "first-client"
    , "testtesttest"
);
