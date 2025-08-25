# Mountain Spring Flow

一个基于DDD领域设计的Java多模块Spring Boot 3.x项目，集成Spring AI和Python桥接功能。

## 项目架构

```
mountain-spring-flow/
├── common-module/          # 公共模块
├── web-module/            # Web服务模块 (REST API)
├── man-module/            # 管理后台模块
├── python-bridge-module/  # Python桥接模块
└── pom.xml               # 根项目配置
```

## 技术栈

- **Spring Boot 3.2.0**
- **Spring AI 0.8.1** (OpenAI集成)
- **DDD领域驱动设计**
- **JPA/Hibernate**
- **MySQL 8.0** (生产环境)
- **MapStruct** (对象映射)
- **Lombok** (代码简化)
- **Jython** (Python集成 - 用于公式计算)

## DDD分层结构

每个模块都遵循DDD分层架构：

```
module/
├── domain/           # 领域层
│   ├── model/        # 领域模型
│   └── repository/   # 仓储接口
├── application/      # 应用层
│   ├── service/      # 应用服务
│   ├── dto/          # 数据传输对象
│   └── mapper/       # 对象映射
├── infrastructure/   # 基础设施层
│   └── repository/   # 仓储实现
└── interfaces/       # 接口层
    └── controller/   # REST控制器
```

## 模块说明

### 1. Common Module
- 提供DDD基础类和通用工具
- 包含BaseEntity、AggregateRoot等基础领域模型
- 提供统一的响应格式BaseResponse

### 2. Web Module
- REST API服务
- 端口：8080
- 示例：用户管理API
- 集成Spring AI

### 3. Management Module
- 管理后台界面
- 端口：8081
- 使用Thymeleaf模板引擎
- 集成Spring Security

### 4. Python Bridge Module
- Java-Python集成，专门用于公式计算和数据处理
- 端口：8082
- 支持Jython和ProcessBuilder两种方式执行Python脚本
- 提供数学计算、数据分析等公式处理功能
- 与MySQL数据库分离，Python仅用于计算逻辑

## 快速开始

### 1. 构建项目
```bash
mvn clean install
```

### 2. 启动各模块

**Web模块：**
```bash
cd web-module
mvn spring-boot:run
```

**Python桥接模块：**
```bash
cd python-bridge-module
mvn spring-boot:run
```

### 3. 测试API

**创建用户：**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "firstName": "Test",
    "lastName": "User"
  }'
```

**执行Python脚本：**
```bash
curl -X POST http://localhost:8082/api/python/execute \
  -H "Content-Type: text/plain" \
  -d 'print("Hello from Python!")'
```

## 配置说明

### 环境变量
- `OPENAI_API_KEY`: OpenAI API密钥 (用于Spring AI)

### 数据库配置
项目已迁移到MySQL数据库，使用MyBatis Plus作为ORM框架：

| 模块 | 数据库名 | 端口 | 用途 | 技术栈 |
|------|----------|------|------|--------|
| Web模块 | mountain_web | 8080 | 用户管理、REST API | MyBatis Plus + MySQL |
| 管理模块 | mountain_man | 8081 | 后台管理、系统配置 | MyBatis Plus + MySQL |
| Python桥接模块 | 无数据库 | 8082 | 公式计算、数据处理 | 纯Python计算引擎 |

### MySQL安装和配置

#### 1. 安装MySQL 8.0
```bash
# macOS (使用Homebrew)
brew install mysql
brew services start mysql

# Ubuntu/Debian
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql

# Windows
# 下载MySQL Installer从官网安装
```

#### 2. 配置MySQL
```bash
# 登录MySQL
mysql -u root -p

# 创建数据库
CREATE DATABASE mountain_web CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE mountain_man CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE mountain_python CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 创建用户并授权（可选）
CREATE USER 'mountain'@'localhost' IDENTIFIED BY 'mountain123';
GRANT ALL PRIVILEGES ON mountain_web.* TO 'mountain'@'localhost';
GRANT ALL PRIVILEGES ON mountain_man.* TO 'mountain'@'localhost';
GRANT ALL PRIVILEGES ON mountain_python.* TO 'mountain'@'localhost';
FLUSH PRIVILEGES;
```

#### 3. 更新配置文件
在每个模块的`application.yml`中更新数据库连接配置：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/[数据库名]?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: [用户名]
    password: [密码]
```

#### 4. 初始化数据库
项目启动时会自动执行`schema.sql`和`data.sql`脚本创建表结构和初始化数据。

### 数据库连接参数说明
- **驱动**: `com.mysql.cj.jdbc.Driver`
- **URL格式**: `jdbc:mysql://localhost:3306/[数据库名]?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true`
- **方言**: `org.hibernate.dialect.MySQL8Dialect`

## 开发指南

### 添加新模块
1. 创建新的Maven模块
2. 继承父项目配置
3. 按照DDD分层结构组织代码
4. 添加相应的application.yml配置

### 领域模型设计
1. 继承BaseEntity或AggregateRoot
2. 使用JPA注解定义实体
3. 在repository包中定义仓储接口
4. 在service包中实现应用服务

## 部署说明

### Docker部署
```bash
# 构建镜像
mvn clean package
docker build -t mountain-spring-flow .

# 运行容器
docker run -p 8080:8080 -e OPENAI_API_KEY=your-key mountain-spring-flow
```

## 贡献指南
1. 遵循DDD设计原则
2. 保持代码整洁和可测试性
3. 添加适当的单元测试
4. 更新文档

## 许可证
MIT License