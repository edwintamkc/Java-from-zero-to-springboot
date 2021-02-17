import com.test.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        // 1. 必定要寫，獲取 applicationContext object
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 2. 用applicationContext獲取bean
        Hello hello = (Hello)context.getBean("hello");
        System.out.println(hello.toString());

    }
}
