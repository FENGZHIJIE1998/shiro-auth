# shiro-auth
#### 快速搭建前后端分离的权限管理系统 
提供一套基于SpringBoo+shiro的权限管理思路.
前后端都加以控制,做到按钮/接口级别的权限。

并没有加加密等过多限制，避免增加大家学习的负担。
#####本文只是提供一套基于shiro前后端分离的思路。
根据这个思路我们可以最终使用Spring的拦截器，或者servlet的Filter实现同样的权限管理

### 演示地址 https://shiro.itaem.top

### 默认用户
|用户|	角色|	权限|
|  ----  | ----  | ---- |
|Jack|	SVIP|	select;save;delete;update|
|Rose|	VIP|	  select;save;update|
|Paul|	P|	    select|

### 后台基于 Springboot JPA Swagger Shiro
### 前端基于 VUE ElementUI
### 前端代码： https://github.com/FENGZHIJIE1998/shiro-vue
### 详细介绍请戳博客 https://blog.csdn.net/weixin_42236404/article/details/89319359
#### 里面有详细的使用介绍以及实现思路。

## sql文件在/resources/doc下