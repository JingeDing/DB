# 基于Vue+SpringBoot前后端分离的教务管理系统

## 😝技术栈

🌈前端=> Vue+Element UI

🎯后端=> Spring Boot

⚡ 前后端交互=>Axios

⛅数据库=>MySQL 5.7

🌏URL=>http://1.15.22.18/

---

## 配置项目

### 1.前端

首先需要在本地安装并配置**Vue3**的运行环境，新建一个Vue3项目，在**main.js**中导入以下依赖

~~~javascript
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from "axios";
import VueAxios from "vue-axios";

import("@/assets/css/global.css")

createApp(App).use(store).use(router).use(ElementPlus).use(VueAxios,axios).mount('#app')
~~~

以上框架具体的安装与配置方法详见各官方文档

- [Vue.js ](https://v3.vuejs.org/)
- [A Vue 3 UI Framework | Element Plus ](https://element-plus.gitee.io/en-US/)
- [Axios (axios-http.com)](https://axios-http.com/)

### 2.后端

后端主要采用的是**SpringBoot**框架，持久层采用**MyBatis**，数据库采用**MySQL**

确保你的电脑已经安装 **JDK1.8 **及以上的版本，在IDEA或者其他的编辑器中新建一个SpringBoot项目，通过**Maven**导入一些相应的jar包，具体内容在该项目主分支下SoringBoot目录下的**pom.xml**文件中。

然后将 resources 目录下的 application.properties 文件名修改为**application.yml**，该文件是后端项目的配置文件，本项目使用的配置如下：

~~~yaml
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource # datasource type
    driver-class-name: com.mysql.cj.jdbc.Driver #driver name
    url: jdbc:mysql:///test?useSSL=true&useUnicode=true&characterEncoding=UTF-8 #URL
    username:
    password:
  mvc: #set jsp mapper location: return "index" /index.jsp
    view:
      prefix: /
      suffix: .jsp
mybatis:
  type-aliases-package: com.example.springboot.pojo #set mybatis type alias  com.test.studentmanager.pojo
  mapper-locations: classpath:/mapper/*Mapper.xml #set mapper-locations:  classpath:/mapper/*Mapper.xml

server:
  port: 8181

# Paging configuration
pagehelper:
  helper-dialect: mysql
  reasonable: true
  support-methods-arguments: true
  params: count=countSql
~~~

以上提及到的各框架安装与配置方法详见各官方文档

* [Spring | Home](https://spring.io/)

* [mybatis – MyBatis 3 | Introduction](https://mybatis.org/mybatis-3/)
* [Maven – Welcome to Apache Maven](https://maven.apache.org/)

### 3.数据库

该项目采用的数据库管理系统是**MySQL**，字符集采用**utf8**，排序规则采用**utf8_general_ci**。

数据库中创建具体的表、视图、触发器等的sql代码可在主分支下MySQL-code目录下查看

