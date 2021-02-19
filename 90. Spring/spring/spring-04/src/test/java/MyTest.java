import com.test.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 留意dynamic proxy代理嘅係 interface，唔係實際嘅interface implementation，所以傳入 interface嘅class object
        UserService userService = context.getBean("userService", UserService.class);

        userService.add();
    }
}
