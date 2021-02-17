import com.test.pojo.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    public static void main(String[] args) {
        ApplicationContext Context = new ClassPathXmlApplicationContext("applicationContext.xml");
        People people = Context.getBean("people", People.class);
        System.out.println(people);

//        Output
//        People{name='Tom', cat:meow, dog:wangwang}
    }
}
