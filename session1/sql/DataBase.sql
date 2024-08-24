-- 1. Tạo cơ sở dữ liệu Login
--CREATE DATABASE Login;

-- 2. Chọn cơ sở dữ liệu Login để sử dụng
USE Login;

-- 3. Tạo bảng users
CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

-- 4. Thêm dữ liệu mẫu
INSERT INTO users (username, password) VALUES ('ty', '123');
INSERT INTO users (username, password) VALUES ('admin', 'adminpass');

-- 5. Kiểm tra dữ liệu
SELECT * FROM users;

SELECT * FROM users WHERE username = 'ty' AND password = 123
