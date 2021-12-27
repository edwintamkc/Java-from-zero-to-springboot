package src.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

public class test04 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class c1 = Class.forName("src.reflection.Student2");

        // 通過反射獲取annotation
        Annotation[] annotations = c1.getAnnotations();
        for(Annotation annotation : annotations){
            System.out.println(annotation);
        }

        // 獲取annotation's value
        Table table = (Table)c1.getAnnotation(Table.class);
        String value = table.value();
        System.out.println(value);

        // 獲取class入面指定field嘅annotation
        Field f = c1.getDeclaredField("name");
        MyField myfield1 = f.getAnnotation(MyField.class);
        System.out.println(myfield1.columnName());
        System.out.println(myfield1.type());
        System.out.println(myfield1.length());
    }
}

@Table ("Student")
class Student2{
    @MyField(columnName = "db_id", type = "int", length = 10)
    private int id;
    @MyField(columnName = "db_age", type = "int", length = 10)
    private int age;
    @MyField(columnName = "db_name", type = "varchar", length = 3)
    private String name;

    public Student2(){

    }

    public Student2(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}



@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyField{
    String columnName();
    String type();
    int length();
}