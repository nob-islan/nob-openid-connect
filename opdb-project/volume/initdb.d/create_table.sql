-- データベース作成
CREATE DATABASE OPDB;
USE OPDB;

-- テーブル作成
CREATE TABLE IF NOT EXISTS user_info (
    user_id VARCHAR(10) NOT NULL
    , password VARCHAR(32) NOT NULL
    , user_name VARCHAR(20) NOT NULL
    , PRIMARY KEY(user_id)
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
        (user_id, password, user_name); -- DBのどのカラムに相当するかを明記