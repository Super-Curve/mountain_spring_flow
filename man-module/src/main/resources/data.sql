-- 使用管理模块数据库
USE mountain_man;

-- 插入初始管理员数据
INSERT IGNORE INTO administrators (username, email, password_hash, full_name, role) VALUES
('superadmin', 'admin@mountain.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iOEcalwu', 'Super Administrator', 'SUPER_ADMIN'),
('manager1', 'manager1@mountain.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iOEcalwu', 'Manager One', 'MANAGER');

-- 插入系统配置
INSERT IGNORE INTO system_configs (config_key, config_value, description) VALUES
('site.name', 'Mountain Spring Flow', '网站名称'),
('site.description', '基于DDD的多模块Spring Boot应用', '网站描述'),
('security.max_login_attempts', '5', '最大登录尝试次数'),
('security.lockout_duration_minutes', '30', '账户锁定时间（分钟）'),
('logging.level', 'INFO', '日志级别');

-- mountain_python数据库的初始化数据
USE mountain_python;
-- 这里可以添加Python桥接模块的初始化数据