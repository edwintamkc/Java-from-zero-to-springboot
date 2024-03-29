 ![image-20210216161417817](notes.assets/image-20210216161417817.png)

# 1. Intro

**Spring框架**是 Java平台的一個開源的全棧（Full-stack）應用程式框架和控制反轉容器實現，一般被直接稱為 Spring。該框架的一些核心功能理論上可用於任何 Java 應用，但 Spring 還為基於Java企業版（Java EE)平台構建的 Web 應用提供了大量的擴充支援。Spring 沒有直接實現任何的編程模型，但它已經在 Java 社群中廣為流行，基本上完全代替了企業級JavaBeansEJB）模型 

> document: https://docs.spring.io/spring-framework/docs/current/reference/html/core.html



`核心功能模組`

- 基於 JavaBeans 的採用控制反轉（Inversion of Control，IoC）原則的組態管理，使得應用程式的組建更加簡易快捷。
- 資料庫事務的一般化抽象層，允許聲明式（Declarative）事務管理器，簡化事務的劃分使之與底層無關。
- JDBC 抽象層提供了有針對性的異常等級（不再從 SQL 異常中提取原始代碼），簡化了錯誤處理，大大減少了程式設計師的編碼量。再次利用 JDBC 時，你無需再寫出另一個'終止'（finally）模組。並且面向 JDBC 的異常與 Spring 通用資料存取物件（Data Access Object）異常等級相一致。
- 以資源容器，DAO 實現和事務策略等形式與 Hibernate，JDO 和 MyBatis 、SQL Maps 整合。利用控制反轉機制全面解決了許多典型的 Hibernate 整合問題。所有這些全部遵從 Spring 通用事務處理和通用資料存取物件異常等級規範。



`重點`

- Spirng 免費，open source
- IOC (inversion of control)、AOP (aspect-oriented programming)



## 1.2 maven dependency

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.0.RELEASE</version>
</dependency>
<!--需要與mybatis做整合，所以要呢個↓-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.0.RELEASE</version>
</dependency>
```



## 1.3 組成

![image-20210216161300762](notes.assets/image-20210216161300762.png)





# 2. IOC

## 2.1 intro

> 之前寫一個應用，要寫以下幾個file
>
> - UserDao
> - UserDaoImplementation
> - UserService
> - UserServiceImplementation
>
> 當user發出請求，由 service layer call dao layer，完成操作

咁 Dao 點同 Service產生關係呢？就係係 service layer加一個 dao object

![image-20210216163354410](notes.assets/image-20210216163354410.png)

> 當user發出請求，就 new 一個 service object，等 service layer call dao layer
>
> ![image-20210216163550681](notes.assets/image-20210216163550681.png)

咁樣嘅話有一個問題：每次 user發出新請求，姐係request一個新嘅 dao object，我地 (programmer) 需要改 service layer嘅code

![image-20210216163849376](notes.assets/image-20210216163849376.png)

就係改呢個 service implementation， new 另外一個 dao implementation

`但係咁做係program控制呢個dao object，太麻煩，我地應該改變諗法，令user控制呢個object`

![image-20210216164302329](notes.assets/image-20210216164302329.png)

> 就係唔寫死呢個 UserDao object，而係`提供一個setter`，當user改變需求，直接用setter new一個對應嘅dao object就得

![image-20210216164535857](notes.assets/image-20210216164535857.png) 



## 2.2 總結

>之前係program 主動create object，控制權係programmer手上
>
>用setter之後，會根據user嘅requirement create，控制權係 user手上

![image-20210216165236674](notes.assets/image-20210216165236674.png)

- IOC（Inverse Of Control）控制反轉，即把建立物件的權利交給框架，也就是指將物件的建立、物件的儲存、物件的管理交給了spring容器

- spring容器是spring中的一個核心模組，用於管理物件，底層可以理解為是一個map集合
- `IOC: 獲得object嘅方式反轉`
- `Spring實現IOC嘅係 IOC container，其實現方法是 Dependency Injection (DI)`

> 而用spring嘅話，spring IoC container就會幫我地控制 object 嘅`創建`以及`管理`，programmer唔需要再new object，set property； 稱為IoC





# 3. Hello Spring

`1. pojo`

```java
package com.test.pojo;

public class Hello {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "str='" + str + '\'' +
                '}';
    }
}
```

`2. 寫bean`

> bean簡介：In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container are called beans. A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container.
>
> 重點：
>
> - `bean係object`
> - bean被Spring IoC container 管理 (由呢個container  instantiated, assembled)
> - 一個program由多個bean構成
>
> 官網：https://docs.spring.io/spring-framework/docs/current/reference/html/core.html

​		`beans.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
	<!--id自己set，class綁定pojo-->
    <!--property係pojo嘅property，可以用value set佢嘅value-->
    <bean id="hello" class="com.test.pojo.Hello">
        <property name="str" value="Hello Spring!"/>
    </bean>

</beans>
```

`3. test`

```java
public class Test {
    public static void main(String[] args) {
        // 1. 必定要寫，獲取Spring container 
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 2. 用applicationContext獲取bean
        Hello hello = (Hello)context.getBean("hello");
        System.out.println(hello.toString());

    }
}
```

output如下

![image-20210216172446692](notes.assets/image-20210216172446692.png)



> 反思：
>
> 1. Hello 呢個object由邊個create？
>    - 由Spring
> 2. Hello object入面 str呢個property由邊個set？
>    - 由Spring container set

> 總結：
>
> 以前我地需要 set好曬 pojo入面嘅property，又要 new；
>
> 宜家將控制權交俾 IoC container，由佢負責 new object及set property，我地只需要寫好bean，需要用果陣去container攞！
>
> `唔再需要改source code，要實現唔同操作，只需要更改xml 文件`



# 4. How IoC create object

> 下面嘅例子基於`User pojo`

`User pojo`

```java
package com.test.pojo;

public class User {
    private String name;

    public User(){
        
    }

//  public User(String name) {  有參構造
//      this.name = name;
//  }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        // 1. 必定要寫，獲取Spring container 
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 2. 用applicationContext獲取bean
        User user = (User)context.getBean("user");
        System.out.println(hello.toString());
    }
}
```

- `當我地用 context.getBean() method，就會用default constructor create object`
- `留意：getBean時會創建該xml下所有bean object`

- 如果要用有參數嘅constructor:

  1. index賦值 (contructor argument index)

     ```xml
     <bean id="user" class="com.test.pojo.user">
     	<constructor-arg index="0" value="Tom"/>
     </bean>
     ```

     為 index = 0 嘅argument賦值為Tom

  2. 參數名

     ```xml
     <bean id="user" class="com.test.pojo.user">
     	<constructor-arg name="name" value="Tom"/>
     </bean>
     ```

     

     

# 5. Spring configuration

spring xml file提供嘅配置並不多，有以下幾個：

- beans
- bean
- alias
- import
- description



## 5.1 bean

> bean tag 下有好多property，當中 id，class，name比較重要

```xml
<bean id="hello" class="com.test.pojo.Hello" name="myHello,myHelloBean">
    <property name="str" value="Hello Spring!"/>
</bean>
```

- id = bean object name
- class = 呢個bean object綁定嘅 pojo
- name = alias，可以設置多個，用逗號分隔

## 5.2 alias

> 為 bean object起一個別名 (留意唔係pojo object)

```xml
<bean id="hello" class="com.test.pojo.Hello">
    <property name="str" value="Hello Spring!"/>
</bean>

<alias name="hello" alias="myHello"/>
```



## 5.3 import

> import其他 spring configuration file，`留意只能應用於spring configuration file，其他文件需要用其他方法`

`applicationContext.xml`

```xml
<import resource="bean1.xml"/>
<import resource="bean2.xml"/>
<import resource="bean3.xml"/>
```



# 6. Dependency injection

> `Dpendency: bean object creation depends on container`
>
> `Injection: bean object's properties injected by container`
>
> Dependency injection分為三種：
>
> - constructor injection 
> - setter injection
> - other
>
> document: https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-dependencies

`呢節會用到嘅pojo有兩個`

`Address.java`

```java
public class Address {
    private String address;
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                '}';
    }
}
```

`Student.java (省略getter setter constructor)`

```java
public class Student {
    private String name;
    private Address address;
    private String[] books;
    private List<String> hobbies;
    private Map<String,String> card;
    private Set<String> games;
}
```

## 6.1 constructor injection

#4 已經提過，呢度只寫出常用嘅寫法

`參數名`

![image-20210217110139397](notes.assets/image-20210217110139397.png)

```xml
<bean id="user" class="com.test.pojo.Student">
	<constructor-arg name="name" value="Tom"/>
</bean>
```

`reference`

![image-20210217110145713](notes.assets/image-20210217110145713.png)

```xml
<bean id="address" class="com.test.pojo.Address">
    <property name="address" value="HK"/>
</bean>

<bean id="student" class="com.test.pojo.Student">
    <constructor-arg name="name" value="Tom"/>

    <property name="address" ref="address"/>
</bean>
```

留意ref 係reference bean object，需要預先寫一個bean先



## 6.2 setter injection

![image-20210217110154585](notes.assets/image-20210217110154585.png)

```xml
<bean id="student" class="com.test.pojo.Student">
    <property name="books">
        <array>
            <value>english</value>
            <value>maths</value>
            <value>whatever</value>
        </array>
    </property>

    <property name="hobbies">
        <list>
            <value>coding</value>
            <value>guitar</value>
            <value>movie</value>
        </list>
    </property>

    <property name="card">
        <map>
            <entry key="octopus" value="1235412"/>
        </map>
    </property>

    <property name="games">
        <set>
            <value>LOL</value>
            <value>AOA</value>
            <value>BOB</value>
        </set>
    </property>
</bean>
```



`Test.java`

```java
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());

//        Output:
//        Student{name='Tom',
//                address=Address{address='HK'},
//                books=[english, maths, whatever],
//                hobbies=[coding, guitar, movie],
//                card={octopus=1235412},
//                games=[LOL, AOA, BOB]}
    }
}
```





## 6.3 other

> - p namespace : 
>
>   對應setter，與setter類似
>
>   需要 xmlns:p="http://www.springframework.org/schema/p"
>
> - c namespace :
>
>   對應constructor，與constructor類似
>
>   需要 xmlns:c="http://www.springframework.org/schema/c" 
>
> document: https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-p-namespace

`p namespace`

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p" <!--留意用之前需要import-->
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean name="classic" class="com.example.ExampleBean">
        <property name="email" value="someone@somewhere.com"/>
    </bean>

    <bean name="p-namespace" class="com.example.ExampleBean"
        p:email="someone@somewhere.com"/>
</beans>
```

> 兩個bean都係一樣，上面用setter，下面用 p:property name = "value" ，十分方便！
>
> 留意p namesapce係用setter做injection

`c namespace`

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:c="http://www.springframework.org/schema/c"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="beanTwo" class="x.y.ThingTwo"/>
    <bean id="beanThree" class="x.y.ThingThree"/>

    <!-- traditional declaration with optional argument names -->
    <bean id="beanOne" class="x.y.ThingOne">
        <constructor-arg name="thingTwo" ref="beanTwo"/>
        <constructor-arg name="thingThree" ref="beanThree"/>
        <constructor-arg name="email" value="something@somewhere.com"/>
    </bean>

    <!-- c-namespace declaration with argument names -->
    <bean id="beanOne" class="x.y.ThingOne" c:thingTwo-ref="beanTwo"
        c:thingThree-ref="beanThree" c:email="something@somewhere.com"/>

</beans>
```

> 留意使用c namespace嘅話，pojo需要有參構造及無參構造
>
> 因為c namesapce用constructor做injection



## 6.4 bean scope

bean 有不同作用域，官網介紹如下，呢度只講singleton及prototype

![image-20210217112405645](notes.assets/image-20210217112405645.png)

`singleton (default)`

![image-20210217112422925](notes.assets/image-20210217112422925.png)

`prototype`

![image-20210217112528978](notes.assets/image-20210217112528978.png)



# 7. Autowired

> 之前寫嘅spring xml 都要手動寫bean property
>
> Spring提供一種自動寫bean property嘅方法：autowired，主要有兩種方法
>
> - byName
> - byType
>
> 下面嘅例子會寫一個新嘅pojo

`Cat.java`

```java
public class Cat {
    public String shout(){
        return "meow";
    }
}
```

`Dog.java`

```java
public class Dog {
    public String shout(){
        return "wangwang";
    }
}
```

`People.java (省略getter setter toString)`

```java
public class People {
    private String name;
    private Cat cat;
    private Dog dog;
}
```

> 準備工作完成，下面寫 xml



## 7.1 byName

`applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="cat" class="com.test.pojo.Cat"/>
    <bean id="dog" class="com.test.pojo.Dog"/>

<!--    <bean id="people" class="com.test.pojo.People">-->
<!--        <property name="name" value="Tom"/>-->
<!--        <property name="cat" ref="cat"/>-->
<!--        <property name="dog" ref="dog"/>-->
<!--    </bean>-->

    <bean id="people" class="com.test.pojo.People" autowire="byName">
        <property name="name" value="Tom"/>

    </bean>

</beans>
```

> comment左嘅位置係原本嘅寫法，手動set property
>
> 而下面嘅bean寫左 autowire="byName"，佢會根據 name 為 bean object set property，因為 people 呢個pojo有Cat cat， Dog dog。 cat/dog 呢兩個名同上面 bean object嘅id一樣，佢就會自動reference，用對應嘅setter set properties
>
> `留意名必須一樣`

`test.java`

```java
public class test {
    public static void main(String[] args) {
        ApplicationContext Context = new ClassPathXmlApplicationContext("applicationContext.xml");
        People people = Context.getBean("people", People.class);
        System.out.println(people);
        
//        Output
//        People{name='Tom', cat:meow, dog:wangwang}
    }
}
```



## 7.2 byType

`applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="cat" class="com.test.pojo.Cat"/>
    <bean id="dog" class="com.test.pojo.Dog"/>

    <bean id="people" class="com.test.pojo.People" autowire="byType">
        <property name="name" value="Tom"/>

    </bean>

</beans>
```

> byType同上面類似，不過唔係根據 name，而係根據 pojo嘅 type (class type)
>
> 因為 People入面 Cat cat/ Dog dog 呢兩個property嘅 type已經生成為bean 
>
> 所以佢可以autowired



## 7.3 annotation (@Autowired)

> autowired除左係applicationContext.xml 入面寫，都可以用annotation實現，步驟如下

`1. 導入文件及寫bean`

xmlns:context="http://www.springframework.org/schema/context"

**開啟annotation: conext: annotation-config/>**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context" <!--import呢個-->
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context  
        https://www.springframework.org/schema/context/spring-context.xsd"> 
	<!--enable annotation-->
    <context:annotation-config/>

	<bean id="cat" class="com.test.pojo.Cat"/>
    <bean id="dog" class="com.test.pojo.Dog"/>
    <bean id="people" class="com.test.pojo.People"/>


</beans>
```

`2. 使用 (用翻上面People, Dog ,Cat嘅例子)`

`People.java (省略getter setter toString)`

```java
public class People {
    private String name;
    @Autowired                 // 使用annotation
    private Cat cat;
    @Autowired				   // 使用annotation
    private Dog dog;
}
```

> 留意：@Autowired 係智能識別，當ioc container入面只有`一個`  Cat type/ Dog type就會用byType
>
> 有`多個` ，就用byName
>
> 亦可以用qualifier手動設置byName，例如
>
> ```java
> @Autowired
> @Qualifier(value="dog123")
> private Dog dog
> ```
>
> 佢就會自動注入 id="dog123"嘅bean



# 8. Annotation

> document：https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-annotation-config

需要嘅bean definition:

`applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

寫完呢個就可以寫pojo

`User.java`

```java
public class User {
    private String name = "Tom";
}
```



## 8.1 @Component

寫完pojo之後，本來應該就去寫翻對應嘅bean，例如

```xml
<bean id="user" class="com.test.pojo.User"/>
```

但係我地可以用Annotation寫 -> ==@Component==，呢一句就等同於上面果行code

`User.java`

```java
@Component  //留意呢行
public class User {
    private String name = "Tom";
}
```

> 留意：必須先在 `applicationContext.xml`開啟要掃描Annotation嘅package，例如呢個User pojo係pojo package下，所以要咁寫：
>
> ```xml
> <!--enable scanning-->
> <context:component-scan base-package="com.test.pojo"/>
> <context:annotation-config/>
> ```
>
> bean name就係 class name嘅小寫，所以係 user

@Component有幾個衍生嘅annotation，分別對應於每一層layer

- controller layer (@Controller)
- service layer (@Service)

- dao/mapper layer (@Repository)

呢四個annotation功能係一樣：將某個Class -> bean

重溫：view -> controller -> service -> mapper -> pojo -> database



## 8.2 @Value

`User.java`

```java
@Component  
public class User {
  	@Value("Tom")  //留意呢行
    private String name;
}
```

> 至於set property，之前寫bean就可以用constructor, setter, namespace等等嘅方法寫
>
> 用annotation嘅話就係用@Value，上面兩個 annotation加埋等價於
>
> ```xml
> <bean id="user" class="com.test.pojo.User">
>     <property name="name" value="Tom"/>
> </bean>
> ```



## 8.3 @Scope

`User.java`

```java
@Component
@Scope("singleton") //留意呢行
public class User {
  	@Value("Tom")
    private String name;
}
```

> 等同於 set bean scope



## 8.4 conclusion

xml VS annotation

- xml更萬能，適用於任何場合，並且維護方便，只需要改xml文件
- annotation簡單，方便使用，但係維護相對複雜，因為要改嘅話要一個一個pojo咁改

通常 bean 會寫係 `applicationContext.xml`入面，而annotation只做 property injection

> 其他常用annotation請參閱官網：https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-annotation-config



# 9. AOP

> AOP (aspect oriented programming)!

首先用講proxy



## 9.1 Proxy

> 點解要學proxy (代理)？因為 proxy就係Spring AOP嘅底層，分為
>
> - static proxy (靜態代理)
> - dynamic proxy (動態代理)

![image-20210218220239057](notes.assets/image-20210218220239057.png)

代理有四個角色：

- subject (一般係interface / abstract class)
  - 只提供 method (可以理解為`行為`，被代理嘅角色 && 中介要做嘅行為)
- real subject (被代理人)
  - 自己會 implement abstract subject入面嘅method，因為佢本身就係想做呢件事
  - 被中介代理
- proxy (中介)
  - 代理 real subject
  - implement abstract subject入面嘅method，因為既然佢要幫real subject做某件事，佢自然要有能力完成果件事 (implement abstract subject's method)
  - `實現 real subject嘅行為之餘，中介可以有其他行為`
- client (客戶)
  - 想訪問 real subject嘅人，但係宜家比中介攔住，所有嘢都要經過中介進行

> 租樓例子：subject = 租呢個行為；real subject = 包租婆； proxy = 地產經紀； client = 你
>
> 背景：包租婆以及地產經紀implement subject (佢地都擁有放租呢個method)
>
> 你想租樓，但係無辦法直接搵到包租婆同佢傾，咁唯有經過地產經紀 (proxy)。地產經紀除左`幫包租婆(real subject)租樓 (實際要做嘅嘢)俾你之外，亦可能帶你睇樓、收中介費、加鹽加醋等等 (額外要做嘅嘢)`。
>
> 而呢個過程，就叫 Proxy 代理



## 9.2 aop概念

![image-20210218224117395](notes.assets/image-20210218224117395.png)

例子：

宜家有 add(), search(), delete(), change() 四個methods，你老闆要求你每call 一個function就output一個 log msg。

你有兩個方法：

1. 去翻 add(), search(), etc 方法內部增加 log msg output
2. 加一層 log (中介)，去做翻 add(), search()等等嘅methods，並且`加入額外行為 (output log msg)`

> 揀方法一嘅話並不可取，因為會改變source code，增加好多工作量 (寫bug)
>
> 而方法二就係最好，寫多一層中介去output，`橫切入去(aspect oriented)`完成要求
>
> 呢個就係 A O P !



## 9.3 add log例子

### 9.3.1 dependency

> 所有aop都需要先導入jar

```xml
<dependencies>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.4</version>
    </dependency>
</dependencies>
```



### 9.3.2 準備工作

> 呢個例子將會係原有嘅service implementation之上，增加一個 log output

`UserService (interface)`

```java
public interface UserService {
    public void add();
    public void delete();
    public void update();
    public void query();
}
```

只有四個簡單 methods

`UserServiceImpl`

```java
public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("add a user");
    }

    @Override
    public void delete() {
        System.out.println("delete a user");
    }

    @Override
    public void update() {
        System.out.println("update a user");
    }

    @Override
    public void query() {
        System.out.println("query a user");
    }
}
```

> 目標：用aop係呢四個methods執行前/後增加一個log output

==實現目標==

> 有三種方式實現aop：
>
> 1. 用Spring提供嘅aop interface 
> 2. DIY一個class，然後寫aop
> 3. 用annotation



### 9.3.3 第一種方式

用Spring提供嘅aop interface 完成，首先寫翻 log 同 afterLog做 log output

`Log (method運行前用)`

```java
public class Log implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + "'s " + method.getName() + " is running!");
    }
}
```

> MethodBeforeAdvice係 Java aop入面一個interface
>
> advice可以理解為行為，係呢度代表 `進入real method之前做啲咩`
>
> 參數詳解：
>
> method = 實際上要run嘅method，例如要call add() method，咁呢度就係 add()
>
> args = 傳入嘅參數
>
> target = 目標object，例如call add() 就係 UserServiceImpl呢個object

`AfterLog (method運行後用)`

```java
public class AfterLog implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Finish " + method.getName() + ". Return value is " + returnValue);
    }
}
```

> implements AfterReturningAdvice，`行完real method之後要做啲咩`
>
> 呢度嘅arguments加多左嘅 returnValue
>
> 除左以上寫嘅兩個，aop有大量interface可以寫，如下圖

![image-20210219154007870](notes.assets/image-20210219154007870.png)

> 寫完曬 real method同 log之後當然要寫bean

`applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--註冊bean-->
    <bean id="userService" class="com.test.service.UserServiceImpl"/>
    <bean id="log" class="com.test.log.Log"/>
    <bean id="afterLog" class="com.test.log.AfterLog"/>

    <!--aop-->
    <aop:config>
        <!--1. 設置切入點:即係邊度執行-->
        <!--execution(return type, 要執行嘅位置)-->
        <!--第一個* 代表任何類型return type，位置.* 代表該位置下所有methods， 位置.*(..)代表該method傳入嘅參數不限-->
        <aop:pointcut id="pointcut" expression="execution(* com.test.service.UserServiceImpl.*(..))"/>

        <!--2. 用advisor，將 要執行嘅嘢，同切入點綁定-->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>

</beans>
```

> 留意需要先寫 aop definition
>
> xmlns:aop="http://www.springframework.org/schema/aop"
>
> xsi:schemaLocation="http://www.springframework.org/schema/aop
>         https://www.springframework.org/schema/aop/spring-aop.xsd"

1. 註冊bean

   寫法同之前一樣，留意除左要註冊 real subject，log (額外嘅行為) 都要註冊

2. 設置aop切入點

3. 寫advisor

跟住就可以用

`MyTest.java`

```java
public class MyTest {
    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 留意dynamic proxy代理嘅係 interface，唔係實際嘅interface implementation，所以傳入 interface嘅class object
        UserService userService = context.getBean("userService", UserService.class);

        userService.add();
    }
}
```

![image-20210219163315048](notes.assets/image-20210219163315048.png)

> 本身call UserService嘅add()，應該只會output "add a user"呢一句
>
> 但係用aop後，為呢個method增加額外嘅行為，所以output多兩個log!



### 9.3.4 第二種方式

> 直接DIY 一個java class去做output，然後係applicationContext.xml做翻aop

`DIYLog`

```java
public class DIYLog {
    public void before(){
        System.out.println("========before========");
    }
    public void after(){
        System.out.println("========after========");
    }
}
```

留意呢個class 並無implement aop所提供嘅interface，只係做簡單output

`applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--註冊bean-->
    <bean id="userService" class="com.test.service.UserServiceImpl"/>
    <bean id="diyLog" class="com.test.log.DIYLog"/>
	
    <!--aop-->
    <aop:config>
        <aop:aspect ref="diyLog">
            <!--設置切入點-->
            <aop:pointcut id="pointcut" expression="execution(* com.test.service.UserServiceImpl.*(..))"/>
            <!--加入aop-->
            <aop:before method="before" pointcut-ref="pointcut"/>
            <aop:after method="after" pointcut-ref="pointcut"/>
        </aop:aspect>
    </aop:config>
</beans>
```

> 直接寫bean，寫pointcut，然後切入

![image-20210219202121430](notes.assets/image-20210219202121430.png)



### 9.3.5 第三種方式

> 用annotation實現aop，直接係 log上面寫
>
> 呢度用翻`DIYLog`做例子

`DIYLog`

```java
@Aspect // 將呢個class標記為aspcet
public class DIYLog {
    // 加入aop
    @Before("execution(* com.test.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("========before========");
    }
    //加入aop
    @After("execution(* com.test.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("========after========");
    }
}
```

`applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--註冊bean-->
    <bean id="userService" class="com.test.service.UserServiceImpl"/>
    <bean id="diyLog" class="com.test.log.DIYLog"/>
	
    <!--最尾就開翻 aop annotation-->
    <aop:aspectj-autoproxy/>
	
</beans>
```



# 10. 整合mybatis

步驟：

1. import jar file

   - junit

   - mybatis

   - mysql

   - spring

   - aop

   - mybatis-spring `(new)`

   ```xml
       <dependencies>
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.12</version>
           </dependency>
   
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis</artifactId>
               <version>3.5.2</version>
           </dependency>
   
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>5.1.47</version>
           </dependency>
           
           <!--spring 操作database必須要呢個-->
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-jdbc</artifactId>
               <version>5.1.9.RELEASE</version>
           </dependency>
   
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-webmvc</artifactId>
               <version>5.1.9.RELEASE</version>
           </dependency>
   
           <dependency>
               <groupId>org.aspectj</groupId>
               <artifactId>aspectjweaver</artifactId>
               <version>1.9.4</version>
           </dependency>
   
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis-spring</artifactId>
               <version>2.0.2</version>
           </dependency>
           
   		<!--lombok，為左偷懶==-->
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
               <version>1.16.10</version>
           </dependency>
       </dependencies>
   ```

2. 寫code
3. 測試



## 10.1 回憶mybatis

寫mybatis嘅步驟：

> denpendency -> 連接database -> mybatis-config.xml -> utility (getSqlSession) -> pojo -> mapper -> mapper method implementation (xml / annotation) -> test

每個步驟要做嘅嘢請參閱mybatis notes！



## 10.2 mybatis-spring

官網：https://mybatis.org/spring/#

什麼是mybatis-spring?

- MyBatis-Spring會幫助你將MyBatis代碼無縫地整合到Spring中。它將允許MyBatis參與到Spring的事務管理之中，創建映射器mapper和`SqlSession`並註入到bean中，以及將Mybatis的異常轉換為Spring的`DataAccessException`。最終，可以做到應用代碼不依賴於MyBatis，Spring或MyBatis-Spring。



`更換dataSource(以及database environment)位置`

`mybatis-config.xml`

![image-20210219214412116](notes.assets/image-20210219214412116.png)

**以前嘅database environment，以及datasource係寫係 mybatis-config.xml入面，用mybatis-spring嘅話，會轉位置寫：寫係applicationContext.xml入面，寫法如下**

`留意spring -> database需要先加dependency： spring-jdbc`

`applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--DataSource: 使用spring嘅datasource 替換mybatis配置嘅datasource-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?userSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
    </bean>

</beans>
```

同時，以前mybatis亦需要用 SqlSessionFactoryBuilder -> SqlSessionFactory -> SqlSession，做法通常係寫一個utility，提供 return SqlSession方法，需要果陣用

宜家都係轉位置寫：寫係applicationContext.xml入面

`applicationContext.xml`

```xml
<!--sqlSessionFactory-->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
</bean>
```

> 呢個Spring configuration file亦可以連接到 Mybatis嘅configuration file，只需要係bean加入property，如下 (呢度直接寫係 sqlSessionFactory呢個bean度)

```xml
<!--sqlSessionFactory-->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="configLocation" value="classpath:mybatis-config.xml"/>
    <property name="mapperLocations" value="classpath:com/test/mapper/*.xml"/>
</bean>
```

綁定後，好多mybatis configuration都可以寫係spring configuration file bean入面，例如mapper，上面嘅例子就加左一個mapper，`係呢度加左之後，唔需要係mybatis-config.xml再加`



整完 sqlSessionFactory嘅bean，當然輪到 sqlSession

```xml
<!--sqlSession-->
<!--SqlSessionTemplate 等同於 sqlSession，spring換左個名-->
<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    <!--只能使用constructor做DI，因為template入面無setter method-->
    <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory"/>
</bean>
```

去到呢一步，所有sqlSession嘅配置已經完成

之後就係寫 service implementation

> 根據翻之前寫 spring 嘅做法，寫完 `service interface`就應該寫 `service interface implementation`，跟住將呢個 service implementation註冊成bean，交俾spring控制
>
> 宜家我地將 spring 同mybatis結合，所以寫嘅唔係 service嘅 implementation；而係mapper嘅implementation，再將mapper implementation 註冊成bean

`UserMapperImpl.java`

```java
public class UserMapperImpl implements UserMapper{
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> selectUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
```

**implements 翻 Mapper，而呢個 UserMapper有一個 selectUser() 嘅方法 (SQL已經係UserMapper.xml寫左)，override之後，我地係呢個method應該return users。但係如果要用mapper執行SQL去return user，應該要用 sqlSession嚟做，所以係呢個class入面整一個 sqlSession嘅field**



跟住跟翻 spring做法，寫完 implementaion之後將呢個file註冊成bean

`applicationContext.xml`

```xml
<bean id="userMapper" class="com.test.mapper.UserMapperImpl">
    <property name="sqlSession" ref="sqlSession"/>
</bean>
```

再寫 test function

原本用mybatis寫test function就會係咁

`MyTest.java`

```java
@Test
public void test01() throws IOException {
    String resource = "mybatis-config.xml";
    InputStream stream = Resources.getResourceAsStream(resource);
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
    SqlSession sqlSession = factory.openSession(true);

    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    List<User> userList = mapper.selectUser();

    for (User user : userList) {
        System.out.println(user);
    }

    sqlSession.close();

}
```

雖然之前係用 utility封裝好  return sqlSession，為左直觀顯示出分別，呢度寫埋出黎

改為spring-mybatis結合後，所有嘢已經係 applicationContext.xml 配置好，或者係相應嘅MapperImpl.java 整好曬sqlSession，所以我地只需要用翻 spring嘅做法去做就可以

```java
@Test
public void test02(){
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
    for(User user : userMapper.selectUser()){
        System.out.println(user);
    }
}
```



## 10.3 簡化

之前係UserMapperImpl.java 入面，寫左一個 SqlSession field，再提供setter方法俾 spring container做injection

spring提供左一個叫 SqlSessionDaoSupport 嘅class，將呢個步驟都省略埋，直接extends佢就可以用 getSqlSession()

簡化後如下

`UserMapperImpl.java`

```java
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper{
    /* 之前嘅寫法
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }*/

    @Override
    public List<User> selectUser() {
        SqlSessionTemplate sqlSession = getSqlSession();  // 可以直接用，已經提供
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
```

留意，雖然係唔需要再寫SqlSession，由SqlSessionDaoSupport呢個class提供，但係呢個class都需要一個 SqlSessionFactory 去整 SqlSession，所以我地需要係 bean傳入一個SqlSessionFactory

`applicationContext.xml`

```xml
<bean id="userMapper" class="com.test.mapper.UserMapperImpl">
    <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
</bean>
```





## 10.4 總結

1. 寫datasource (bean)
2. 寫 sqlSessionFactory (bean)
3. 寫sqlSession (bean)
4. 寫 implementation，呢個class入面應該要有 sqlSessionTemplate 呢個field，並且完成本身應該要做嘅嘢
5. test



# 11. 回顧transaction

> `一係全部成功，一係全部失敗`
>
> 有4個特點 (`ACID`):
>
> - atomicity 原子性 (最小嘅單位，`同一個`transaction中嘅operation一係全部一齊完成，一係全部都失敗)
>   - 銀行轉賬例子: A轉$200俾B，咁A減錢同B加錢要同時發生，唔可以得一個
> - consistency 一致性 (transaction發生前後嘅數據`完整性`必須保持一致)
>   - A同B在轉賬前，一共有$1000，轉賬完都要係1000
> - isolation 隔離性 (多個用戶訪問DB時，DB為每一個用戶開transaction，並且每個transaction之間互相隔離，不被其他transaction干擾)
>   - 假設B原本有500，A同C想`同時`轉$250俾B，如果無隔離，A同C嘅轉賬都係建基於B有500呢個基礎，所以兩個一齊轉最尾B都係得 750，但係實際上應該係1000
> - durability 持久性 (transaction被提交後，對DB的改變係永久)
>   - 假設轉賬呢個transaction提交之前，server死左，咁重啟之後DB嘅data應該係transaction提交之前嘅數據

下面會寫一個例子模擬transaction失敗，再寫如何用spring寫transaction



## 11.1 模擬錯誤

方法：UserMapper.java內有3個functions，分別係 query，add，delete。當query時，用 add()及delete()增加及刪除一個user。

`UserMapper.java`

```java
public interface UserMapper {
    public List<User> selectUser();

    public int addUser(User user);

    public int deleteUser(int id);
}
```

`UserMapper.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.test.mapper.UserMapper">
    <select id="selectUser" resultType="user">
        select * from mybatis.user;
    </select>

    <insert id="addUser" parameterType="com.test.pojo.User">
        insert into mybatis.user (id, name, pwd) VALUES (#{id}, #{name}, #{pwd});
    </insert>

    <delete id="deleteUser" parameterType="int">
        deletes from mybatis.user where id = #{id};
    </delete>
</mapper>
```

> 為左出現錯誤，將 delete 寫為 deletes 

`UserMapperImpl.java`

```java
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper{

    @Override
    public List<User> selectUser() {
        User user = new User(5, "test!!", "abcdefg");
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        mapper.addUser(user);  // 新增一個 5 號user
        mapper.deleteUser(5);  // 由於 delete SQL 錯誤，並不會刪除

        return mapper.selectUser();
    }

    @Override
    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }
}
```

係 selectUser()入面 增加及刪除一個user，但係由於delete SQL有問題，佢肯定唔會刪除，咁addUser()究竟有無用呢？

`MyTest.java`

```java
@Test
public void test01(){
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserMapper mapper = context.getBean("userMapper", UserMapper.class);
    List<User> users = mapper.selectUser(); // 獲取users，並且 add(), delete()
    for (User user : users) {
        System.out.println(user);
    }
}
```

`結果`

![image-20210220102715931](notes.assets/image-20210220102715931.png)

當然會出 error，因為SQL寫錯左！但係我地睇一睇 database user table

![image-20210220102757748](notes.assets/image-20210220102757748.png)

發現 5 號user成功增加，明顯有問題



## 11.2 mybatis-spring transaction

> spring document: https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/transaction.html
>
> mybatis-spring document: https://mybatis.org/spring/transactions.html

分為兩種：

- declarative transaction management (寫一個bean！將會講呢個)
- programmatic transaction management (係program入面處理！唔會講呢個，要用可以睇document)



## 11.3 declarative transaction

> 呢度嘅寫法係根據 mybatis-spring document
>
> `會用埋aop，所以要aop dependency: aspectjweaver`

`applicationContext.xml`

```xml
<!--1. add declarative transaction bean-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <constructor-arg ref="dataSource" />
</bean>

<!--2. 用tx 設置邊個 method 需要加transaction-->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <tx:attributes>
        <tx:method name="addUser" propagation="REQUIRED"/>
        <tx:method name="deleteUser" propagation="REQUIRED"/>
        <tx:method name="selectUser" read-only="true"/>  <!--query 不需要transaction-->
        <tx:method name="*" propagation="REQUIRED"/> <!--亦可以設置 *，所有methods都開啟transaction-->
    </tx:attributes>
</tx:advice>

<!--3. 設置 tx aop-->
<aop:config>
    <aop:pointcut id="txPointCut" expression="execution(* com.test.mapper.*.*(..))"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
</aop:config>
```

當我地再run 下面呢個test (同之前一樣)

`MyTest.java`

```java
@Test
public void test01(){
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserMapper mapper = context.getBean("userMapper", UserMapper.class);
    List<User> users = mapper.selectUser();
    for (User user : users) {
        System.out.println(user);
    }
}
```

`結果`

![image-20210220105011119](notes.assets/image-20210220105011119.png)

可以見到 user 5無被加到table中，因為transaction中一個失敗，就會導致所有都失敗
