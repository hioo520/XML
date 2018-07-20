package xml.core;


import xml.annotation.XMLClosure;
import xml.annotation.XMLEntity;
import xml.common.Invoke;
import xml.constant.XMLConstant;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Features: XML工具方法
 * @Date:
 * @Author: hihuzi  2018/6/18 11:51
 */

public class XMLToolImpl {


    // tips 实现方法
    public static <T> List<T> getXml(String XML, T t, XMLConstant constant) throws Exception {
        // tips 思路
        List<T> result = new ArrayList<T>();
        Class<?> clazz = t.getClass();
        List<String> isAnnotation = new ArrayList<>();//tips 单行属性
        Map fieldsMap = new HashMap();
        Map annotationfieldsMap = new HashMap();
        Field[] fields = clazz.getDeclaredFields();
        //tips 取出所有字段
        if (clazz.isAnnotationPresent(XMLEntity.class)) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(XMLClosure.class))
                    fieldsMap.put(field.getName(), field.getAnnotation(XMLClosure.class).name());
                else
                    fieldsMap.put(field.getName(), field.getName());
                if (field.isAnnotationPresent(XMLClosure.class)) {
                    if (field.getAnnotation(XMLClosure.class).isLonely()) {
                        for (String parameter : field.getAnnotation(XMLClosure.class).attributes()) {
                            isAnnotation.add(parameter);
                        }
                    }
                }
            }
        }
        Iterator fieldsmaps = fieldsMap.entrySet().iterator();
        while (fieldsmaps.hasNext()) {
            Map.Entry entry = (Map.Entry) fieldsmaps.next();
            String key0 = entry.getKey().toString();
            String key1 = entry.getValue().toString();
            if (isAnnotation.contains(key0)) {
                annotationfieldsMap.put(key0, key1);
            }
        }
        Iterator key = annotationfieldsMap.keySet().iterator();
        while (key.hasNext()) {
            fieldsMap.remove(key.next());
        }
        // tips 开始处理XML
        Element root = DocumentHelper.parseText(XML).getRootElement();
        List<Element> elements = root.elements();
        for (Element element : elements) {
            Object obj = t.getClass().newInstance();
            Iterator iterator = annotationfieldsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key3 = String.valueOf(entry.getKey());
                String values = String.valueOf(entry.getValue());
                StringBuffer name = new StringBuffer();
                name.append("set").append(key3.substring(0, 1).toUpperCase()).append(key3.substring(1));
                String value = null;
                try {
                    value = element.attributeValue(values);
                } catch (Exception e) {
                }
                if (value != null) {
                    Invoke.putValue(obj, key3, value, constant);
                }
            }
            Iterator iterators = fieldsMap.entrySet().iterator();
            while (iterators.hasNext()) {
                Map.Entry entry = (Map.Entry) iterators.next();
                String key4 = String.valueOf(entry.getKey());
                String values = String.valueOf(entry.getValue());
                StringBuffer name = new StringBuffer();
                name.append("set").append(key4.substring(0, 1).toUpperCase()).append(key4.substring(1));
                String value = null;
                try {
                    value = element.element(values).getStringValue();
                } catch (Exception e) {
                }
                if (value != null) {
                    Invoke.putValue(obj, key4, value, constant);
                }
            }
            result.add((T) obj);
        }
        return result;
    }

    public static String getXml(List list, String sign, StringBuffer XML) throws Exception {


        StringBuffer xml = XML;
        for (Object entity : list) {
            Class clazz = entity.getClass();
            Object newEntity = entity;
            List<String> temporaryDeposits = new ArrayList<>();
            List<String> exclude = new ArrayList<>();
            String property = "";
            Field[] fields = clazz.getDeclaredFields();
            List<String> notAnontationfields = new ArrayList(fields.length);
            Boolean isHavingEndTag = ((XMLEntity) clazz.getAnnotation(XMLEntity.class)).isHavingEndTag();//类

            if (clazz.isAnnotationPresent(XMLEntity.class)) {
                for (Field field : fields) {
                    if (field.isAnnotationPresent(XMLClosure.class)) {
                        exclude.add(field.getName());
                        XMLClosure XMLClosure = field.getAnnotation(XMLClosure.class);
                        temporaryDeposits.add(XMLClosure.name());
                        xml.append("<" + XMLClosure.name());
                        for (String parameter : XMLClosure.attributes()) {
                            exclude.add(parameter);
                            property = String.valueOf(Invoke.getValue(clazz, newEntity, parameter));
                            xml.append(" " + parameter + "=\"" + property + "\"");
                        }
                        xml.append(">");
                    } else {
                        notAnontationfields.add(field.getName());
                    }
                }
                //tips 清除已经被使用的 属性
                notAnontationfields.removeAll(exclude);
                //tips 第二层 没有注解的处理
                for (String parameter : notAnontationfields) {
                    /*排除是集合的属性*/
                    if (("String".equals(clazz.getDeclaredField(parameter).getType().getSimpleName()))) {
                        if (!isHavingEndTag) {
                            xml.append("<" + parameter + ">");
                            property = String.valueOf(Invoke.getValue(clazz, newEntity, parameter));
                            xml.append("\"" + property + "\"");
                            xml.append("</" + parameter + ">");
                        } else {
                            xml.append("<" + parameter + " " + sign + "=");
                            property = String.valueOf(Invoke.getValue(clazz, newEntity, parameter));
                            xml.append("\"" + property + "\"");
                            xml.append("/>");
                        }
                    } else {
                        //tips 添加 一个结束标签
                        for (int i = temporaryDeposits.size() - 1; i > 0; i--) {
                            xml.append("</" + temporaryDeposits.get(i) + ">");
                            temporaryDeposits.remove(temporaryDeposits.get(i));
                            break;
                        }
                        //tips 没有注解是 LIST 处理
                        List sonList = (List) Invoke.getValue(clazz, newEntity, parameter);
                        //tips 重复调用
                        getXml(sonList, sign, xml);
                    }
                }
                for (int i = temporaryDeposits.size() - 1; i >= 0; i--) {
                    xml.append("</" + temporaryDeposits.get(i) + ">");
                }
            }
        }
        return xml.toString();
    }

}