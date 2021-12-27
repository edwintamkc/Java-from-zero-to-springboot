package src.reflection;

public class test01 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 1. 通過class name獲取
        Class c1 = Person.class;
        System.out.println(c1.hashCode());

        // 2. 通過filepath獲取
        Class c2 = Class.forName("src.reflection.Person");
        System.out.println(c2.hashCode());

        // 3. 通過object獲取 (需要首先create object)
        Person person = new Person();
        Class c3 = person.getClass();
        System.out.println(c3.hashCode());
    }
}

class Person{
    private int age;
    private String name;

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
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

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}


