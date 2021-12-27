import com.test.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
