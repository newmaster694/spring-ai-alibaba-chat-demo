理解你的问题: doing
收集相关的信息: doing
# Spring AI Alibaba Demo 项目说明文档

本项目是一个基于 **Spring Boot** 和 **Spring AI Alibaba** 构建的简单聊天助手应用，通过调用阿里云 DashScope 提供的 API 实现智能问答和流式对话功能。

---

## 📦 技术栈

- **Java 版本**: JDK 21
- **构建工具**: Maven
- **框架与库**:
    - Spring Boot 3.5.3
    - Spring Web MVC
    - Spring AI Alibaba (`spring-ai-alibaba-starter-dashscope`)
    - Lombok（用于简化实体类开发）
- **AI 服务提供方**: 阿里云 DashScope 平台

---

## 🧠 功能特性

### 1. 简单问答接口
- 请求方式：`GET`
- 路径：`/hello-world/simple/chat`
- 支持自定义提问内容，默认为：“你好，很高兴认识你，能简单介绍一下自己吗？”
- 返回值：完整的模型回复内容。

### 2. 流式对话接口（SSE）
- 请求方式：`GET`
- 路径：`/hello-world/stream/chat`
- 支持流式输出，逐步返回模型生成的内容。
- 可用于前端实时显示对话进展。

---

## 📁 项目结构概览

```
src/
├── main/
│   ├── java/
│   │   └── com.newmaster/
│   │       ├── config/
│   │       │   └── ChatClientConfig.java  # 配置 ChatClient Bean
│   │       ├── controller/
│   │       │   └── ChatController.java      # 对外暴露的 REST 接口
│   │       └── Application.java             # 启动类
│   │
│   └── resources/
│       └── application.yml                  # 应用配置文件
└── pom.xml                                  # Maven 依赖管理
```


---

## 🛠️ 依赖说明

```xml
<dependencies>
    <!-- Spring Boot Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring AI Alibaba DashScope Starter -->
    <dependency>
        <groupId>com.alibaba.cloud.ai</groupId>
        <artifactId>spring-ai-alibaba-starter-dashscope</artifactId>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```


---

## ⚙️ 配置说明

在 [application.yml](file://K:\java_workspace\spring-ai-alibaba-demo\target\classes\application.yml) 中需配置阿里云 DashScope 的 API Key：

```yaml
spring:
  ai:
    dashscope:
      api-key: your_dashscope_api_key_here
```


你可以从 [DashScope 官网](https://help.aliyun.com/document_detail/498574.html) 获取你的 API 密钥。

---

## 🏃‍♂️ 快速启动

1. 安装并配置好 JDK 21。
2. 执行以下命令启动项目：

```bash
mvn spring-boot:run
```


3. 访问接口测试：
    - 简单问答：[http://localhost:8080/hello-world/simple/chat?query=你的问题](http://localhost:8080/hello-world/simple/chat?query=你的问题)
    - 流式对话：[http://localhost:8080/hello-world/stream/chat?query=你的问题](http://localhost:8080/hello-world/stream/chat?query=你的问题)

---

## 💡 注意事项

- 请确保网络可访问 `https://dashscope.aliyuncs.com`。
- 若出现连接异常，请检查代理设置或尝试使用 OkHttp 替代默认 HTTP 客户端。
- 建议在生产环境中启用 SSL 证书验证，避免使用不安全的 HTTPS 配置。

---

## 📝 示例请求

### GET /simple/chat

```bash
curl "http://localhost:8080/hello-world/simple/chat?query=如何学习Java"
```


### GET /stream/chat

```bash
curl "http://localhost:8080/hello-world/stream/chat?query=宇宙有多大"
```


浏览器中访问会自动以流式文本输出结果。

---

## 📚 相关资源

- [Spring AI Alibaba GitHub](https://github.com/alibaba/spring-ai-alibaba)
- [DashScope 文档中心](https://help.aliyun.com/document_detail/498574.html)

---

欢迎提出建议或提交 PR 来完善本项目！🚀