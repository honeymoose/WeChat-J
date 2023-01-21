# WeChat-J - 微信开发 Java SDK

<p align="center">
    <a href="https://github.com/honeymoose">
        <img height=85 src="https://avatars1.githubusercontent.com/u/45009982?s=200&v=4">
    </a>
    <br>This project builds by JDK 11 and OpenJ9 for JVM.
</p>

* [社区和讨论 (community)](https://www.ossez.com/tag/wechat)

WeChat-J 开发使用的库。

我们旨在提供一个初始化的开发框架，能够让应用在使用 Spring Boot 框架的基础上让你的微信公众号快速接入微信平台。

# 项目说明
我们在网上找了一些微信相关的 SDK，要不就是缺少维护，要不就是集成了非常多的功能，因我们的公众号希望能够尽量的自动化处理，所以我们在已有的基础上进行了一些修改和整合。

## 必要的准备
因微信开发 Java SDK 的开发其实并不非常复杂，主要是通过 HTTP 发送请求并且将获得的返回数据进行一些处理返回对象就可以了。

我们需要调用微信的 HTTP 接口，所以我们需要在 Java 中使用一个 Http 客户端，在当前我们的环境中，我们只使用 OkHttp 来进行实现。

在老的项目中，可能不少人会使用 Apache 的 HttpClient 来进行实现，但因为 OkHttp 广泛的被使用在安卓的手机上，所以使用 OkHttp 会更加简便。

* [Retrofit 是什么](https://www.ossez.com/t/retrofit/14302)
* [微信测试平台获得测试账号](https://www.ossez.com/t/topic/14281)


### 重要信息
1. 项目合作洽谈请联系微信`binary0000`（在微信里自行搜索并添加好友，请注明来意，如有关于SDK问题需讨论请参考下文入群讨论，不要加此微信）。
2. **2022-8-21 发布 [【4.4.0正式版】](https://mp.weixin.qq.com/s/kHg-QHMK6ymbQwTdKFF2lQ)**！
3. 贡献源码可以参考视频：[【贡献源码全过程（上集）】](https://mp.weixin.qq.com/s/3xUZSATWwHR_gZZm207h7Q)、[【贡献源码全过程（下集）】](https://mp.weixin.qq.com/s/nyzJwVVoYSJ4hSbwyvTx9A) ，友情提供：[程序员小山与Bug](https://space.bilibili.com/473631007)
4. 新手重要提示：本项目仅是一个SDK开发工具包，未提供Web实现，建议使用 `maven` 或 `gradle` 引用本项目即可使用本SDK提供的各种功能，详情可参考 **[【Demo项目】](demo.md)** 或本项目中的部分单元测试代码；
5. 微信开发新手请务必阅读【开发文档】（[Gitee Wiki](https://gitee.com/binary/weixin-java-tools/wikis/Home) 或者 [Github Wiki](https://github.com/Wechat-Group/WxJava/wiki)）的常见问题部分，可以少走很多弯路，节省不少时间。
6. 技术交流群：想获得QQ群/微信群/钉钉企业群等信息的同学，请使用微信扫描上面的微信公众号二维码关注 `WxJava` 后点击相关菜单即可获取加入方式，同时也可以在微信中搜索 `weixin-java-tools` 或 `WxJava` 后选择正确的公众号进行关注，该公众号会及时通知SDK相关更新信息，并不定期分享微信Java开发相关技术知识；
7. 钉钉技术交流群：`32206329`（技术交流2群）, `30294972`（技术交流1群，目前已满），`35724728`（通知群，实时通知Github项目变更记录）。
8. 微信开发新手或者Java开发新手在群内提问或新开Issue提问前，请先阅读[【提问的智慧】](https://github.com/ryanhanwu/How-To-Ask-Questions-The-Smart-Way/blob/master/README-zh_CN.md)，并确保已查阅过 [【开发文档Wiki】](https://github.com/wechat-group/WxJava/wiki) ，避免浪费大家的宝贵时间；
9. 寻求帮助时需贴代码或大长串异常信息的，请利用 http://paste.ubuntu.com 

--------------------------------
### 其他说明
1. **阅读源码的同学请注意，本SDK为简化代码编译时加入了`lombok`支持，如果不了解`lombok`的话，请先学习下相关知识，比如可以阅读[此文章](https://mp.weixin.qq.com/s/cUc-bUcprycADfNepnSwZQ)；**
2. 如有新功能需求，发现BUG，或者由于微信官方接口调整导致的代码问题，可以直接在[【Issues】](https://github.com/Wechat-Group/WxJava/issues)页提出issue，便于讨论追踪问题；
3. 如果需要贡献代码，请务必在提交PR之前先仔细阅读[【代码贡献指南】](CONTRIBUTING.md)，谢谢理解配合；
4. 目前本`SDK`最新版本要求的`JDK`最低版本是`8`，使用`7`的同学可以使用`WxJava` `3.8.0`及以前版本，而还在使用`JDK`6的用户请参考[【此项目】]( https://github.com/binarywang/weixin-java-tools-for-jdk6) ，而其他更早的JDK版本则需要自己改造实现。
5. [本项目在开源中国的页面](https://www.oschina.net/p/weixin-java-tools-new)，欢迎大家积极留言评分 🙂
6. SDK开发文档请查阅 [【开发文档Wiki】](https://github.com/wechat-group/WxJava/wiki)，部分文档可能未能及时更新，如有发现，可以及时上报或者自行修改。
7. **如果本开发工具包对您有所帮助，欢迎对我们的努力进行肯定，可以直接前往[【托管于码云的项目首页】](http://gitee.com/binary/weixin-java-tools)，在页尾部分找到“捐助”按钮进行打赏，多多益善 😄。非常感谢各位打赏和捐助的同学！**
8. 各个模块的Javadoc可以在线查看：[weixin-java-miniapp](http://binary.ac.cn/weixin-java-miniapp-javadoc/)、[weixin-java-pay](http://binary.ac.cn/weixin-java-pay-javadoc/)、[weixin-java-mp](http://binary.ac.cn/weixin-java-mp-javadoc/)、[weixin-java-common](http://binary.ac.cn/weixin-java-common-javadoc/)、[weixin-java-cp](http://binary.ac.cn/weixin-java-cp-javadoc/)、[weixin-java-open](http://binary.ac.cn/weixin-java-open-javadoc/)
9. 本SDK项目在以下代码托管网站同步更新:
* 码云：https://gitee.com/binary/weixin-java-tools
* GitHub：https://github.com/wechat-group/WxJava

---------------------------------
### Maven 引用方式
注意：最新版本（包括测试版）为 [![Maven Central](https://img.shields.io/maven-central/v/com.github.binarywang/wx-java.svg)](http://mvnrepository.com/artifact/com.github.binarywang/wx-java)，以下为最新正式版。

```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>（不同模块参考下文）</artifactId>
  <version>4.4.0</version>
</dependency>
```

  - 微信小程序：`weixin-java-miniapp`   
  - 微信支付：`weixin-java-pay`
  - 微信开放平台：`weixin-java-open`   
  - 公众号（包括订阅号和服务号）：`weixin-java-mp`    
  - 企业号/企业微信：`weixin-java-cp`


### 框架和案例
如果你想登记你的项目，请[访问这里](https://www.ossez.com/t/wechat-j-demo/14303)。

同时，我们也提供了一些开发框架，能够让你直接检出项目就可以直接对微信 SDK 进行接入和测试。

* [公众号 Spring Boot 测试程序](https://github.com/honeymoose/WeChat-Official-Account-Spring)


# 联系方式

请使用下面的联系方式和我们联系。

* [社区和讨论](https://www.ossez.com/tag/chat-gpt)

| 联系方式名称           | 联系方式                                          |
|------------------|-----------------------------------------------|
| 电子邮件（Email）      | [yhu@ossez.com](mailto:yhu@ossez.com)         |
| QQ 或微信（WeChat）   | 103899765                                     |
| QQ 交流群           | 15186112                                      |
| 社区论坛 （Community） | https://www.ossez.com/c/computer-technology/7 |

# 公众平台

我们建议您通过社区论坛来和我们进行沟通，请关注我们公众平台上的账号

## 微信公众号

![](https://cdn.ossez.com/img/cwikius/cwikius-qr-wechat-search-w400.png)

## 头条号

我们也在头条号上创建了我们的公众号，请扫描下面的 QR 关注我们的头条号。

![](https://cdn.ossez.com/img/cwikius/cwikus-qr-toutiao.png)

## 知乎

请关注我们的知乎：https://www.zhihu.com/people/huyuchengus

# License

[WeChat-J is licensed under the MIT License](https://src.ossez.com/honeymoose/WeChat-J/src/branch/main/LICENSE)
