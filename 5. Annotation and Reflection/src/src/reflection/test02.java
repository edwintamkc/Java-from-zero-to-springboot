package src.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class test02 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1 = Class.forName("src.reflection.Person");

        // 獲取class name
        System.out.println(c1.getName());  // 獲取 packet name + class name
        System.out.println(c1.getSimpleName()); // 獲取 class name only

        // 獲取class variable
        Field[] fields = c1.getFields(); // 獲取public var only
        Field[] fields2 = c1.getDeclaredFields(); // 獲取所有var，包括private

        // 獲取指定var的值
        Field name = c1.getDeclaredField("name");

        // 獲取class method
        Method[] methods1 = c1.getMethods(); // 獲取自己及其父類的public method
        Method[] methods2 = c1.getDeclaredMethods(); // 獲取自己的所有method，包括private

        // 獲取指定嘅method
        // 第二個參數係果個method所需嘅參數嘅class，用作區分overload嘅method，null指果個method不需要參數
        Method getName = c1.getMethod("getName",null); //獲取getName method
        Method setName = c1.getMethod("setName", String.class); // 需要一個string作參數傳入setName method

        // 獲取constructor
        Constructor constructor1 = c1.getConstructor();


    }
}
