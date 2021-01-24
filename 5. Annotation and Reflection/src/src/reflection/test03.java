package src.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class test03 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // create Class object
        Class c1 = Class.forName("src.reflection.Person");

        // create a Person object by Class object c1
        Person person = (Person)c1.newInstance(); // 默認係用無參構造
        System.out.println(person);
        // output: Person{age=0, name='null'}

        // 如果想用有參構造點算？ 你要知道佢有參構造需要咩arguments先
        // 用getDeclaredConstructor攞到先，呢度直接跳過
        // 知道之後做法如下，先create一個constructor，再用呢個去create Person object
        Constructor constructor = c1.getDeclaredConstructor(int.class, String.class);
        Person person2 = (Person)constructor.newInstance(20, "Tom");
        System.out.println(person2);
        // output: Person{age=20, name='Tom'}


        // 通過reflection call Person入面嘅method
        // 先通過反射獲取class入面嘅method
        Method setName = c1.getDeclaredMethod("setName", String.class);
        setName.invoke(person,"Mary"); // 再用invoke去調用呢個method
        System.out.println(person.getName());
        // output: Mary


        // 通過reflection 改變person object嘅屬性
        Field age = c1.getDeclaredField("age");
        age.setAccessible(true); // age係private，本身係改唔到，但係Reflection可以用呢個方法去強行獲取private field
        age.set(person,25);
        System.out.println(c1.getDeclaredMethod("getAge", null).invoke(person));
        // output: 25


    }
}
