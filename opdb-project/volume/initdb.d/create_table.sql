-- データベース作成
CREATE DATABASE OPDB;
USE OPDB;

-- テーブル作成 user_info
CREATE TABLE IF NOT EXISTS user_info (
    user_id VARCHAR(10) NOT NULL
    , password VARCHAR(32) NOT NULL
    , PRIMARY KEY(user_id)
);

-- TODO テーブル作成 client_info
CREATE TABLE IF NOT EXISTS client_info (
    client_id VARCHAR(10) NOT NULL
    , client_secret VARCHAR(32) NOT NULL
    , redirect_uri TEXT NOT NULL
    , PRIMARY KEY(client_id)
);

-- user_info投入
LOAD DATA LOCAL INFILE '/csv/user_info.csv'
    INTO TABLE
        user_info
    FIELDS
        TERMINATED BY ','
    LINES
        TERMINATED BY '\n'
    IGNORE 1 ROWS
        (user_id, password);

-- TODO client_info投入
LOAD DATA LOCAL INFILE '/csv/client_info.csv'
    INTO TABLE
        client_info
    FIELDS
        TERMINATED BY ','
    LINES
        TERMINATED BY '\n'
    IGNORE 1 ROWS
        (client_id, client_secret, redirect_uri);
