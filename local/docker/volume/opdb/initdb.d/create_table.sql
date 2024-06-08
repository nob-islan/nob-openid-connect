-- データベース作成
CREATE DATABASE OPDB;
USE OPDB;

-- テーブル作成 user_info
CREATE TABLE IF NOT EXISTS user_info (
    user_id VARCHAR(10) NOT NULL
    , password VARCHAR(32) NOT NULL
    , PRIMARY KEY(user_id)
);

-- テーブル作成 client_info
CREATE TABLE IF NOT EXISTS client_info (
    client_id VARCHAR(10) NOT NULL
    , client_secret VARCHAR(32) NOT NULL
    , redirect_uri TEXT NOT NULL
    , PRIMARY KEY(client_id)
);

-- テーブル作成
CREATE TABLE IF NOT EXISTS authorization_code_info (
    code_id INT NOT NULL AUTO_INCREMENT
    , code_value VARCHAR(32) NOT NULL
    , code_challenge VARCHAR(64) NOT NULL
    , expiration_date_time DATETIME NOT NULL
    , is_deleted BOOLEAN NOT NULL
    , PRIMARY KEY(code_id)
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

-- client_info投入
LOAD DATA LOCAL INFILE '/csv/client_info.csv'
    INTO TABLE
        client_info
    FIELDS
        TERMINATED BY ','
    LINES
        TERMINATED BY '\n'
    IGNORE 1 ROWS
        (client_id, client_secret, redirect_uri);
