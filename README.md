## Malloc-blog

个人博客(SpringBoot+MybatisPlus)

[博客地址](http://malloc.club/)

> 因为没有审美天赋所以前端就不写了，前端是从[**myblog-mybatis**](https://github.com/oneStarLR/myblog-mybatis#myblog-mybaits)拉取的，目前是把后端删了重写，把mybatis更改为mybatisPlus。还新增了一些其他的功能。目前还在完善中，还请关注。

**使用该源码希望能够注明原博客以及源码出处，并禁止商用，谢谢！**

###  一、技术栈

#### **1.前端**

- JS框架：JQuery
- CSS框架：[Semantic UI官网](https://semantic-ui.com/)
- Markdown编辑器：[编辑器 Markdown](https://pandao.github.io/editor.md/)
- 代码高亮：[代码高亮 prism](https://github.com/PrismJS/prism)
- 动画效果：[动画 animate.css](https://daneden.github.io/animate.css/)
- 文章目录：[目录生成 Tocbot](https://tscanlin.github.io/tocbot/)
- 音乐盒[：zplayer](https://gitee.com/supperzh/zplayer)
- 照片墙[：lightbox插件](https://github.com/JavaScript-Kit/jkresponsivegallery)

#### **2.后端**

- 核心框架：SpringBoot 2.3.1
- 项目构建：jdk1.8、Maven 3.6(idea默认配置)
- 持久层框架：MybatisPlus
- 工具：小辣椒（Lombok）
- 模板框架：Thymeleaf
- 加密：MD5加密

**3.数据库**

- MySQL 8.0

### 二、功能需求

因为是个人博客，所以没有做用户权限管理，只是简单的区分了一下普通用户和管理员用户，这里就根据普通用户和管理员用户来讲述功能需求，其实从上一篇博文的前端页面就能大致的看出需求了

#### 1.普通用户

- 查看文章信息：文章列表、推荐文章、文章标题、文章内容、发布时间、访问量以及评论等信息
- 查看分类文章：分类列表、分类文章信息
- 查看时间轴：按照文章时间发布顺序查看文章
- 搜索文章：导航栏右边搜索框根据关键字搜索
- 听音乐：上一曲、下一曲、音量控制、播放顺序控制、查看歌词等
- 留言：留言并回复
- 查看友链：查看并访问博主在友链页面添加的友链连接
- 查看相册信息：相册列表、照片名称、照片拍摄地点、时间、照片描述

#### 2.管理员用户（栈主）

- 拥有普通用户所有功能权限
- 登录：在主页路径下加“/admin”，可进入登录页面，根据数据库的用户名和密码进行登录
- 文章管理：查询文章列表、新增文章、编辑文章、删除文章、搜索文章
- 分类管理：查询分类列表、新增分类、编辑分类、删除分类
- 友链管理：查询友链列表、新增友链、编辑友链、删除友链
- 相册管理：查询相册列表、新增照片、编辑照片、删除照片
- 消息管理：登录后恢复评论留言会显示栈主的头像信息，并能显示删除消息按键，可以对消息进行删除

### 三、数据库设计

> 在原来的博客文章的表上增加了deleted逻辑删除字段，放在数据的误删和丢失。在每张表的字段上都加上了注释。

#### 1.数据表

- 博客数据表：t_blog
- 分类数据表：t_type
- 用户数据表：t_user
- 评论数据表：t_comment
- 留言数据表：t_message
- 友链数据表：t_friend
- 相册数据表：t_picture
