package xml.common;


import xml.constant.XMLConstant;
import xml.util.DateTool;
import xml.util.NullEmptyTool;

import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * TIPS 对对象反射调用set方法配置数据
 *
 * @Author:hihuzi 2018/7/19 10:29
 */

public class Invoke {

    /**
     * TIPS 注入值
     *
     * @Author:hihuzi 2018/7/19 10:26
     */

    public static <E> void putValue(E e, String name, String value, XMLConstant constant) throws Exception {

        Class<?> paramtertype = e.getClass().getDeclaredField(name).getType();
        StringBuffer setName = new StringBuffer().append("set").append(name.substring(0, 1).toUpperCase()).append(name.substring(1));
        Method method = e.getClass().getMethod(setName.toString(), paramtertype);
        methodInvokeValue(e, method, value, paramtertype.getSimpleName(), constant);
    }

    /**
     * TIPS 注入对对象注入值
     *
     * @Author:hihuzi 2018/7/19 10:26
     */

    private static <E> void methodInvokeValue(E e, Method method, String value, String fieldType, XMLConstant constant) throws Exception {

        if (NullEmptyTool.isNNoE(value)) {
            if ("String".equals(fieldType)) {
                method.invoke(e, value);
            } else if ("Date".equals(fieldType)) {
                if (DateTool.isDate(value)) {
                    method.invoke(e, DateTool.parse(value));
                } else {
                    System.out.println("能处理YYYY-MM-DD不能处理" + value);
                }
            } else if ("char".equalsIgnoreCase(fieldType)) {
                method.invoke(e, value.toCharArray()[0]);
            } else if ("Byte".equalsIgnoreCase(fieldType) || "byte".equals(fieldType)) {
                method.invoke(e, Byte.valueOf(value));
            } else if ("Long".equalsIgnoreCase(fieldType) || "long".equals(fieldType)) {
                method.invoke(e, Long.parseLong(value));
            } else if ("Short".equals(fieldType) || "short".equals(fieldType)) {
                method.invoke(e, Short.parseShort(value));
            } else if ("Float".equals(fieldType) || "float".equals(fieldType)) {
                method.invoke(e, Float.parseFloat(value));
            } else if ("Double".equalsIgnoreCase(fieldType) || "double".equals(fieldType)) {
                method.invoke(e, Double.parseDouble(value));
            } else if ("Integer".equals(fieldType) || "int".equals(fieldType)) {
                method.invoke(e, Integer.parseInt(value));
            } else if ("Boolean".equalsIgnoreCase(fieldType) || "boolean".equals(fieldType)) {
                method.invoke(e, Boolean.parseBoolean(value));
            } else if ("BigDecimal".equalsIgnoreCase(fieldType)) {
                method.invoke(e, new BigDecimal(value));
            } else {
                System.out.println("类型错误" + fieldType);
            }
        } else {//tips 针对 value=null or value =""
            if (("String".equals(fieldType) && "".equals(value)))
                method.invoke(e, value);
            else if (("int".equals(fieldType) || "float".equals(fieldType) || "long".equals(fieldType) || "double".equals(fieldType)) && "".equals(value))
                method.invoke(e, 0);
            else if ("boolean".equals(fieldType) && "".equals(value))
                method.invoke(e, false);
            else if ("short".equals(fieldType) && "".equals(value))
                method.invoke(e, (short) 0);
            else if ("byte".equals(fieldType) && "".equals(value))
                method.invoke(e, Byte.parseByte("0"));
            else if ("char".equals(fieldType) && "".equals(value)) {
                //tips 不做处理
            } else
                method.invoke(e, new Object[]{null});
        }
    }

    public static Object getValue(Class clazz, Object newEntity, String name) throws Exception {

        StringBuffer key = new StringBuffer().append("get").append(name.substring(0, 1).toUpperCase()).append(name.substring(1));
        return clazz.getMethod(key.toString(), new Class[]{}).invoke(newEntity);
    }

}
