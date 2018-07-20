package xml.annotation;

import java.lang.annotation.*;

/**
 * @Features: 定义实体类
 * @Date:
 * @Author: hihuzi  2018/6/18 12:15
 */
//@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface XMLEntity {

    String name() default "";

    boolean isHavingEndTag() default true;

}
