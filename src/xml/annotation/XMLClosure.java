package xml.annotation;

import java.lang.annotation.*;

/**
 * @Features: 闭合区间
 * @Date:
 * @Author: hihuzi  2018/6/18 10:22
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface XMLClosure {

    String[] attributes() default {};

    String name() default "";

    boolean isHavingEndTag() default true;

    boolean isLonely() default true;//是否只有自己

}
