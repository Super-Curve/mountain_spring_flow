-- 使用对应的数据库
USE mountain_web;

-- 插入初始用户数据
INSERT IGNORE INTO users (username, email, password_hash, full_name) VALUES
('admin', 'admin@mountain.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iOEcalwu', 'Administrator'),
('user1', 'user1@mountain.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iOEcalwu', 'User One'),
('user2', 'user2@mountain.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iOEcalwu', 'User Two');

-- mountain_man数据库的初始化数据
USE mountain_man;
-- 这里可以添加管理模块的初始化数据

-- mountain_python数据库的初始化数据
USE mountain_python;
-- 这里可以添加Python桥接模块的初始化数据