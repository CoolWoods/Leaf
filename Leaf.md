Leaf  青叶

青木向婉后端项目

项目基于Java，主要使用了SpringBoot，SpringCloud，SpringSecurity，Mybatis等框架

Jdk1.8.0_191

开发工具Idea2019.2版本

包管理工具maven-3.5.2

Tomcat9.0.37

Redis2.8.9

RabbitMQ3.8.8

# 1. 环境搭建



##  1.1 Maven父工程的搭建

在Idea中新建Leaf父工程，按照正常创建Maven工程步骤创建即可。父工程主要用于依赖版本控制。

![image-20200912120944144](C:\Users\Zongmin\AppData\Roaming\Typora\typora-user-images\image-20200912120944144.png)



在父工程的pom.xml文件中加入依赖，可以根据需求添加或者删减依赖，子工程引入的依赖的版本应当统一交由交工程来管理。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>


    <groupId>cn.qingmu</groupId>
    <artifactId>leaf</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>leaf_common</module>
        <module>leaf_eureka</module>
        <module>leaf_user</module>
        <module>leaf_manager</module>
    </modules>

    <name>qingmu</name>
    <description>青木向婉项目</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.3.RELEASE</version>
        <relativePath/>
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <!--锁定SpringCloud版本-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR8</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot</artifactId>
                <version>2.3.3.RELEASE</version>
            </dependency>
            <dependency>
                <groupId>cn.qingmu</groupId>
                <artifactId>leaf_common</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>2.1.3</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>1.1.23</version>
            </dependency>

            <!--分页插件 pagehelper -->
            <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper-spring-boot-starter</artifactId>
                <version>1.3.0</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

## 1.2 Maven子工程的搭建



选中父工程，选择new module这样Idea就会将要创建的module作为父工程的子工程

![image-20200912121909866](C:\Users\Zongmin\AppData\Roaming\Typora\typora-user-images\image-20200912121909866.png)

在子工程的pom.xml文件中加入依赖，需要根据不同的子工程的需求来添加依赖

![image-20200912121637837](C:\Users\Zongmin\AppData\Roaming\Typora\typora-user-images\image-20200912121637837.png)



```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>leaf</artifactId>
        <groupId>cn.qingmu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>leaf_eureka</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
    </dependencies>

</project>
```

以上就是创建一个子工程的方法，创建其它子工程，按照步骤导入不同的依赖即可。



# 2. Eureka服务组件

### 2.1 引入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>leaf</artifactId>
        <groupId>cn.qingmu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>leaf_eureka</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
    </dependencies>

</project>
```

### 2.2 编写启动类

```java
package cn.qingmu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
```

### 2.3 编写配置文件

```yaml
server:
  port: 8989 #eureka运行的端口号

eureka:
  client:
    register-with-eureka: false #是否加入eureka注册表
    fetch-registry: false #还是向eureka请求注册信息表
    service-url:
      defaultZone: http://127.0.0.1:${server.port}/eureka/
```



因为SpringBoot做了大量的配置工作，因此Eureka服务的配置十分的简单，在做完上面的步骤后就已经完成了Eureka服务注册中心的构建。

### 2.4 运行服务

直接运行启动类，浏览器打开localhost:8989可以看到Eureka的运行状态，说明Eureka服务运行正常，但是目前还没有其它的在其上注册。

![image-20200912122623244](C:\Users\Zongmin\AppData\Roaming\Typora\typora-user-images\image-20200912122623244.png)



# 3. Common组件

Common组件并不是一个服务，它主要的功能是向其它的服务提供工具类。

## 3.1 引入依赖

```xml
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.0</version>
        </dependency>
```

## 3.2 编写entity

### 3.2.1 Result 实体类

Result 实体类定义了向客户端返回Json数据的结构，其它所有的api的返回结果，如果使用的是Json格式返回都必须封装到该实体。

Result实体类UML图如下

![image-20200912123749727](C:\Users\Zongmin\AppData\Roaming\Typora\typora-user-images\image-20200912123749727.png)

从UML图中可以获取该实体的构造函数，属性以及方法，因此不再赘述。

### 3.2.1 StatusCode 实体类

Result中的code字段是Integer类型，理论上可以取integer域内的任意值。

但是为了保证code的含义，我们约定，在给code赋值时，只能使用StateCode中提供的字段。

StatusCode实体类UML图如下

![image-20200912124822604](C:\Users\Zongmin\AppData\Roaming\Typora\typora-user-images\image-20200912124822604.png)



其中

- OK=20000  成功
- ERROR=20001  失败
- LOGINERROR=20002 登录失败
- ACCESSERROR=20003 权限不足
- REMOTEERROR=20004 远程调用失败
- REERROR=20005 重复操作



## 3.3 编写Util



### 3.3.1 IdWorker工具类

IdWorker用于生成数据库表中的id字段，在插入数据时需要使用IdWorker生成id。

本项目中使用的是Twitter的 Snowflake编写的JAVA实现方案。

下面的IdWorker Uml图包含了构造方法和普通方法。一般而言，在注入Spring容器之后，我们只需要使用nextId()方法即可。

![image-20200912125931251](C:\Users\Zongmin\AppData\Roaming\Typora\typora-user-images\image-20200912125931251.png)

### 3.3.2 JwtUtil 工具类

1. 了解JWT

   **Json web token (JWT)**, 是为了在网络应用环境间传递声明而执行的一种基于`JSON`的开放标准（(RFC 7519).**定义了一种简洁的，自包含的方法用于通信双方之间以`JSON`对象的形式安全的传递信息。**因为数字签名的存在，这些信息是可信的，**JWT可以使用`HMAC`算法或者是`RSA`的公私秘钥对进行签名。**

2. 编写JWT工具类

   JWT的实现有成熟的算法，这里仅仅只是对其进行一层简单的封装。

   下面是JwtUtil工具类的UML图

   

   ![image-20200912130631001](C:\Users\Zongmin\AppData\Roaming\Typora\typora-user-images\image-20200912130631001.png)

- key key是Jwt的密钥，一定要保管好
- ttl ttl是Jwt的有效时长，单位是毫秒
- createJWT createJWT方法的三个参数分别是id subject 和roles，返回值是JWT字符串
- parseJWT parseJWT方法的参数是createJWT生成的JWT字符串，返回值是Claims类型，用于确认JwtToken



# 4. Login服务组件