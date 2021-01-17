package src.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class test02 {
    @Myannotation2(name = "asdasd")
    public void test00000(){

    }
}


@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface Myannotation2{
    // 留意寫係annotation入面嘅唔係method，而係參數！
    String name() default "";
    int age() default 0;
}

