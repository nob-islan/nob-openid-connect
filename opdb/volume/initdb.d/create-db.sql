-- データベース作成
CREATE DATABASE opdb;
USE opdb;

-- テーブル作成
CREATE TABLE client_info (
    client_id VARCHAR(15) PRIMARY KEY 
    , client_secret VARCHAR(32) NOT NULL
    , redirect_uri TEXT NOT NULL
);

-- 初期データ投入
INSERT INTO client_info (
    client_id
    , client_secret
    , redirect_uri
) VALUES (
    "first-client"
    , "123123123"
    , "http://localhost:8080/auth/token"
);