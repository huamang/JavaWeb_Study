# 前言

本项目是本人学习servlet实现的一个注册登录界面



- 数据库名：java

- 数据库账号密码：
  - 账号：root
  - 密码：123456

- 表名：user



# 思路

1. 配置好数据库环境，设置了三个字段：id，username，password
2. 创建了User类，实现setter和getter方法
3. JDBC工具类提取，使用Druid连接池实现
4. 创建UserDao类来操作数据库
5. servlet的编写，使用请求转发来跳转到结果页面，这里使用了BeanUtils，把浏览器输入的参数封装进User类中



