# OX-ADMIN 后台管理系统
## 开发初衷
最近想写一个项目，找了开源社区上找了一些后台管理框架。功能复杂代码质量参差不齐，而且自己也用不到那么多的功能，不想项目中太多僵尸代码。于是开发了 ox-admin ，发现在开发过程中遇到一些问题，也算是对自己所学的知识进行一个查漏补缺。

OX-ADMIN 名字由来：自己的花名叫青牛，英文中牛 Cow Bull Ox 都可以翻译成"牛"，它们之间有什么区别呢？Cow 是奶牛可以挤出奶的牛，Bull 是公牛角很长很凶的，Ox 才是中国常见的用起来种地的那种牛。所以就命名为"OX-ADMIN"。

让我们像牛一样，勇敢面对生活中的挑战，勤奋耕耘自己的梦想。无论遇到多少坎坷和困难，我们都要坚定地向前迈进，因为只有这样，我们才能收获属于自己的辉煌人生。相信自己的能力，付出努力，收获成就，成为那头无畏的牛，翻过生命的难关，奔向成功的彼岸！
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
   测试账号:test/aa123456
   管理员账号:admin/123456【自觉点，莫要乱操作】
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

