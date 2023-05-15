# OX-ADMIN 后台管理系统
## 简介
一个基于springboot开发的简单、高效、规范的后台管理框架, 项目基于 Spring Boot、 Mybatis-Plus、 Spring Security、Vue、vue-element-admin
的前后端分离的后台管理系统，项目采用分模块开发方式， 权限控制采用 RBAC，支持动态路由

## 技术栈
- 开发语言：Java
- 数据库：Mysql
- Web 前端框架：Vue + Axios
- 后台前端框架：vue-element-admin
- 后端框架：Spring Boot
- 安全框架：Spring security
- 工具类集合：Hutool

## 项目截图
![输入图片说明](https://github.com/java668/ox-admin/blob/main/docs/images/1683383586326.jpg?raw=true
 "微信图片_20190422175210.png")

## 项目演示地址
- 项目演示地址：[ox-admin-web](http://oxadmin.java668.com)
```
   演示账号:test/test123456
   管理员账号:admin/admin123456【自觉点，莫要乱操作】
```

## 前后端项目地址
- 后端项目地址：[ox-admin](https://github.com/java668/ox-admin)
- 前端项目地址：[ox-admin-web](https://github.com/java668/ox-admin-web)

## 打包部署
### ox-admin 打包部署
```sh
1、修改/resources/application-prod.properties配置文件中对应配置，根据部署环境不同，切换配置文件
   mvn clean package -DskipTests -Pprod 
```

### ox-admin-web 打包部署
```sh

1、修改打包对应环境的配置文件
例如：打包prod环境
修改/ox-admin-web/config/prod中的BASE_API变量，修改为对应后台地址
2、打包部署
$ npm install
$ npm run dev
$ npm run build:prod
```

### nginx 参考配置
```sh
    location / {
        root   /usr/local/ox-admin/ox-admin-web;
        try_files $uri $uri/ @router;
        index  index.html index.htm;
    }
    
    location @router {
        rewrite ^.*$ /index.html last;
    }
    
    location ^~ /api {
        rewrite ^/api(.*)$ $1 break;
        proxy_pass  http://localhost:9999/api;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header REMOTE-HOST $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        access_log /usr/local/nginx/logs/ox-admin-access.log main;
        error_log /usr/local/nginx/logs/ox-admin-error.log warn;
    }

```

## SQL脚本
```
ox-admin\docs\ox-admin.sql
```
## 常见问题
```
1、项目中使用了lombok插件，自行百度。
2、最好用IDEA开发工具。
3、项目打包之后，可能由于不同环境打包导致配置文件出问题，无法启动，请先删除classes、target目录，重新运行项目。
```
## 参考资料
  - [vue-element-admin](https://github.com/PanJiaChen/vue-element-admin)
  - [eladmin](https://github.com/elunez/eladmin)
  - [Hutool](https://gitee.com/loolly/hutool)

