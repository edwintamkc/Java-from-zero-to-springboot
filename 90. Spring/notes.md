 ![image-20210216161417817](notes.assets/image-20210216161417817.png)

# 1. Intro

**Spring框架**是 Java平台的一個開源的全棧（Full-stack）應用程式框架和控制反轉容器實現，一般被直接稱為 Spring。該框架的一些核心功能理論上可用於任何 Java 應用，但 Spring 還為基於Java企業版（Java EE)平台構建的 Web 應用提供了大量的擴充支援。Spring 沒有直接實現任何的編程模型，但它已經在 Java 社群中廣為流行，基本上完全代替了企業級JavaBeansEJB）模型。

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

>之前係program 主動創建 object，控制權係programmer手上
>
>用setter之後，會根據user需求創建，控制權係 user手上

![image-20210216165236674](notes.assets/image-20210216165236674.png)

- IOC（Inverse Of Control）控制反轉，即把建立物件的權利交給框架，也就是指將物件的建立、物件的儲存、物件的管理交給了spring容器

- spring容器是spring中的一個核心模組，用於管理物件，底層可以理解為是一個map集合
- `IOC: 獲得object嘅方式反轉`
- `Spring實現IOC嘅係 IOC container，其實現方法是 Dependency Injection (DI)`

> 而用spring嘅話，spring IoC container就會幫我地控制 object 嘅創建以及管理，programmer唔需要再new object，set property； 稱為IoC





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
> 1. Hello 呢個object由邊個創建？
>    - 由Spring創建
> 2. Hello object入面 str呢個property由邊個設置？
>    - 由Spring container設置

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

> 導入其他 spring configuration file

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

兩個bean都係一樣，上面用setter，下面用 p:property name = "value" ，十分方便！

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



## 7.3 annotation

> autowired除左係applicationContext.xml 入面寫，都可以用annotation實現，步驟如下

`1. 導入文件及寫bean`

xmlns:context="http://www.springframework.org/schema/context"

**開啟annotation: conext: annotation-config/>**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context" <!--導入呢個-->
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context  <!--並且加入支持-->
        https://www.springframework.org/schema/context/spring-context.xsd"> <!--並且加入支持-->
	<!--開啟annotation-->
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

