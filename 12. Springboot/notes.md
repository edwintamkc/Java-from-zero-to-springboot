# 1. Hello world

## 1.1 Create project

![image-20211227185718579](notes.assets/image-20211227185718579.png)

Create project by clicking file -> new project -> spring initializr

## 1.2 Project entry

![image-20211227185841888](notes.assets/image-20211227185841888.png)

The project entry is named xxxxApplication, start Tomcat server by running this file

> The default server is Tomcat, and the port number is 8080

![image-20211227190021633](notes.assets/image-20211227190021633.png)

> Open a web, we could see that the application is running

## 1.3 Hello world

![image-20211227190146332](notes.assets/image-20211227190146332.png)

```java
@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello world!";
    }
}
```

Add a controller `in the same level of your springbootApplication file (as shown in the circle)`, and write this classic example. Then run the application again

![image-20211227190523991](notes.assets/image-20211227190523991.png)

We could get in to this page by sending a request to /hello, and the result is correct!

> In springboot, all the (simple) configuration is completed by default. Thus we could run it directly



