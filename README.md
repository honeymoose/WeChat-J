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
* [RxJava 是什么](https://www.ossez.com/t/rxjava/14305)
* [微信测试平台获得测试账号](https://www.ossez.com/t/topic/14281)

### Maven 和依赖 
当前我们还没有把正式版发布到仓库中，我们还在使用 0.0.1-SNAPSHOT 版本进行内部测试。

最好的版本就是下载我们的源代码后 Fork 到你本地，然后直接使用 Maven 来进行编译。

#### 微信公众号（WeChat Java Official Account）
模块名：wechat-j-oa

```xml
<dependency>
    <groupId>com.ossez.wechat</groupId>
    <artifactId>wechat-j-oa</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

#### 微信小程序（WeChat Java Mini Programs）
模块名：wechat-j-mp

```xml
<dependency>
    <groupId>com.ossez.wechat</groupId>
    <artifactId>wechat-j-mp</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

#### 微信支付（WeChat Java Pay）
模块名：wechat-j-pay （模块还在开发，无实际内容，请不要使用。）

#### 企业微信（WeChat Java WeCom）
模块名：wechat-j-work （模块还在开发，无实际内容，请不要使用。）


#### 微信开放平台（WeChat Java Open）
模块名：wechat-j-open

```xml
<dependency>
    <groupId>com.ossez.wechat</groupId>
    <artifactId>wechat-j-open</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### 其他内容
* 任何有关讨论，请访问 [社区](https://www.ossez.com/tag/wechat)，您可以在这里提出功能需求，Bug 修复，问题解答。
* 可以考虑使用 http://paste.ubuntu.com 来对你在提交问题的时候出现的为代码进行简化。

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
