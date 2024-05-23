# 备忘录
---

## 功能
1. 用户登录/注册
2. 笔记本创建/删除/更名
3. 笔记项目创建/删除/修改

## 运行指令
```shell
cd memos # entry project folder
./gradlew bootRun  # run springboot project
```

# 目录结构
- src 源代码目录
  - main 工作代码
    - java.org.lgz.memos
      - controller 控制器层，响应HTTP请求
      - dto 数据传输对象(data transfer object)，定义要返回给前端的内容
      - database 数据库相关
        - model 数据库映射数据类(data class)
        - repository 数据库访问对象(DAO,data access object)
      - service 实现操作的服务层
        - root 服务接口
        - impl 服务接口的实现
      - exception 自定义异常类，如MemoNotFoundException，使用异常可以更好的区分正常响应和错误响应，符合java编程规范。
      - config 配置类
        - SecurityConfig 安全配置类，使用Spring Security配置用户登录，访问范围等等
    - resources
      - application.yml 配置文件，使用yaml语法，比默认的application.properties方便。不同文件名也可用于区分环境，如development/production
  - test 测试代码
    - java.org.lgz.memos 在这个包下编写项目的单元测试
  - build.gradle Gradle的配置文件，在这里编写了项目需要引入的库，编译任务，JDK版本等
  - .gitignore git提交时会忽略的文件目录，如缓存目录，本地IDE配置文件等

## 参考内容
- [使用JWT登录 - Spring Security Sample](https://github.com/spring-projects/spring-security-samples/tree/6.2.x/servlet/spring-boot/java/jwt/login)
- [使用用户名密码登录及请求过滤的配置](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html#servlet-authentication-unpwd)
- [RFC7519 - JWT实现标准文档](https://datatracker.ietf.org/doc/html/rfc7519)
- 