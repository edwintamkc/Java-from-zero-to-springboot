package src.annotation;

import java.lang.annotation.*;

public class test01 {
    public static void main(String[] args) {

    }
}

// 表示以下annotation只可用作注釋method
@Target(value = {ElementType.METHOD})
// 表示annotation係咩地方有效，通常用RUNTIME
@Retention(value = RetentionPolicy.RUNTIME)
// 表示將註解生成係javadoc入面
@Documented
// 表示子類可繼承父類嘅annotation
@Inherited

@interface MyAnnotation{

}