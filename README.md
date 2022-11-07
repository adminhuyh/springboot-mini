#### 项目简介

本项目本着避免重复造轮子的原则，建立一套快速开发JavaWEB项目（springboot-mini），能满足大部分后台管理系统基础开发功能，使得开发人员直接可从业务模块开始，减少大量的重复开发工作。前端框架使用 layui-mini：https://gitee.com/zhongshaofa/layuimini

注：由于后期写了大量的代码没有及时更新，为了避免覆盖之前的版本，本项目已停止更新，项目已迁移至：https://gitee.com/asurplus/asurplus
#### 项目演示

01.  地址：<a target="_blank" href="http://sbootmini.qyzxsm.com/asurplus/login">http://sbootmini.qyzxsm.com/asurplus/login</a>
01.  账户：13888888888
01.  密码：123456

#### 软件架构

01.  SpringBoot 2.3.1.RELEASE，搭建第一个 SpringBoot 项目：https://blog.csdn.net/qq_40065776/article/details/98474699
02.  MyBatis-Plus 3.3.1，SpringBoot 中使用 MyBatis-Plus：https://blog.csdn.net/qq_40065776/article/details/107546643
03.  MyBatis-Plus-Generator，自动生成代码根据，使用说明：https://blog.csdn.net/qq_40065776/article/details/107546643
04.  MySQL 5.6，数据库
05.  Apache-Maven 3.6.0，版本控制工具
06.  Redis，Key-Value 数据库
07.  Apache-Shiro 1.2.4，权限控制框架，使用说明：https://blog.csdn.net/qq_40065776/article/details/107300987
08.  MiniIO，文件服务器，使用说明：https://blog.csdn.net/qq_40065776/category_9999932.html
09.  Ehcache，缓存框架，使用说明：https://blog.csdn.net/qq_40065776/article/details/107560607
10.  Kaptcha，登录验证码，使用说明：https://blog.csdn.net/qq_40065776/article/details/101481607
11.  Lombok，使用说明：https://blog.csdn.net/qq_40065776/article/details/105643530
12.  knife4j，接口文档，使用说明：https://blog.csdn.net/qq_40065776/article/details/107083757
13.  Autopoi，导出 Excel 文件，使用说明：https://blog.csdn.net/qq_40065776/article/details/107824221
14.  Quartz，定时任务，使用说明：https://blog.csdn.net/qq_40065776/article/details/107489728
15.  Thymeleaf，模板引擎，使用说明：https://blog.csdn.net/qq_40065776/article/details/105566038
16.  LayUI，前端框架，官方文档：https://www.layui.com/doc/
17.  layui-mini，前框模板项目，项目地址：https://gitee.com/zhongshaofa/layuimini

#### 安装教程

01.  安装 JDK，1.8以上
02.  安装 Maven，3.6.0 以上
03.  安装 MySQL，5.6 版本
04.  导入数据库文件到 MySQL，数据库文件放置：db 文件夹中
05.  修改配置文件中数据库的连接信息，链接地址，用户名，密码等信息

完成以上步骤，即可正常启动项目

#### 使用说明

01.  数据字典功能，需要加上 @Dict(dictCode = "status") 注解，方可有效，见 com.zyxx.sys.entity.SysUserInfo
02.  文件上传，默认使用 MinIO 作为文件服务器，例子：com.zyxx.sys.controller.SysMinioController
03.  导出 Excel 文件，采用 JeecgBoot 的 Autopoi 导出 Excel，同样支持注解导出，可自定义注解规则，见 com.zyxx.common.excel.AutoPoiDictService 文件，目前只支持简单导出
04.  系统设置及系统监控的代码放置：com.zyxx.sys 包下面，修改此处的代码需慎重
05.  com.zyxx.common 包下面放一些配置类及一些通用工具类
06.  自动生成代码，需要配置数据库连接信息，以及模块名（MODULENAME）和开发人员（AUTHOR）

#### 内置功能

01.  用户登录：用户输入账户密码登录系统
02.  用户管理：该用户主要针对系统用户，为该系统的管理者
03.  角色管理：不同的角色代表着不同的权限
04.  权限管理：不同的权限代表着可以看到不同的页面，及拥有不同的操作权限
05.  数据字典：对一些固定数值进行翻译成文本的操作，详见：https://blog.csdn.net/qq_40065776/article/details/107403576
06.  数据库管理：可以查看数据库表格信息，及字段信息，对数据进行备份和还原
07.  定时任务：定时执行一些约定好的任务，详见：https://blog.csdn.net/qq_40065776/article/details/107489728
08.  登录日志：对每个用户的登录信息进行监控，获取用户的 IP，地理位置等信息
09.  开发日志：主要针对开发者，对现阶段的完成情况进行记录
10.  接口文档：使用自动生成接口文档，快速对接，详见：https://blog.csdn.net/qq_40065776/article/details/107083757
11.  Redis监控：充当 Redis 客户端，可以及时掌握数据情况
12.  自动生成代码：自动生成 controller、service、mapper、xml、entity等文件代码
13.  文件上传：使用 MinIO 做文件服务器，文件单独存放，详见：https://blog.csdn.net/qq_40065776/category_9999932.html

#### 页面展示

<table>
    <tr>
        <td><img src="https://img-blog.csdnimg.cn/20210221200139455.jpg"/></td>
        <td><img src="https://img-blog.csdnimg.cn/2021022120021436.jpg"/></td>
    </tr>
    <tr>
        <td><img src="https://img-blog.csdnimg.cn/2021022120035277.png"/></td>
        <td><img src="https://img-blog.csdnimg.cn/20210221200407151.png"/></td>
    </tr>
    <tr>
        <td><img src="https://img-blog.csdnimg.cn/20210221200423242.png"/></td>
        <td><img src="https://img-blog.csdnimg.cn/20210221200436310.png"/></td>
    </tr>
	<tr>
        <td><img src="https://img-blog.csdnimg.cn/20210221200534463.png"/></td>
        <td><img src="https://img-blog.csdnimg.cn/20210221200551725.png"/></td>
    </tr>
    <tr>
        <td><img src="https://img-blog.csdnimg.cn/20210221200622232.png"/></td>
        <td><img src="https://img-blog.csdnimg.cn/20210221200607350.png"/></td>
    </tr>
</table>

# springboot-mini

[![李洲/springboot-mini](https://gitee.com/asurplus/springboot-mini/widgets/widget_card.svg?colors=ffffff,1e252b,323d47,455059,d7deea,99a0ae)](https://gitee.com/asurplus/springboot-mini)

#### 捐赠

如果觉得还不错，请作者喝杯咖啡吧 ☺

<table>
    <tr>
        <td style="text-align: center">微信扫一扫</td>
        <td style="text-align: center">支付宝扫一扫</td>
    </tr>
    <tr>
        <td><img src="https://img-blog.csdnimg.cn/20210221195740916.png"/></td>
        <td><img src="https://img-blog.csdnimg.cn/20210221195829103.jpg"/></td>
    </tr>
</table>

#### 关注

欢迎关注我的微信公众号

<img src="https://img-blog.csdnimg.cn/20210221200018475.jpg" alt="微信公众号" />
