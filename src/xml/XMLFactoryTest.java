package xml;

import xml.bean.Body;
import xml.bean.Sms;
import xml.bean.Title;
import xml.core.XMLTool;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLFactoryTest {

    XMLFactory factory = new XMLTool();

    @Test
    void getXml() throws Exception {

        List list = new ArrayList();
        Title title = new Title();
        title = getFillEntity(title);
        for (int i = 0; i < 3; i++) {
            Body body = new Body();
            body = getFillEntity(body);
            title.addBodys(body);
        }
        list.add(title);
        String xml = factory.getXML(list);
        System.out.println(xml);
        Pattern pattern = Pattern.compile("\\/>");
        Matcher matcher = pattern.matcher(xml);
        while (matcher.find()) {
            System.err.println(matcher.replaceAll("/>" + "\n"));
        }

    }

    @Test
    public void getXml1() throws Exception {


        String str0 = "<result><Result id='我是ID' cc='23423'><user_task_id>-1</user_task_id><Mobile>453453453453</Mobile><Message>你好</Message><state>成功</state><exec_time>2018/6/27 9:53:54</exec_time></Result>";
//2018/6/27 9:53:54
        String resp = "<?xml version='1.0' encoding='UTF-8'?><bookstore><book id='1'><name>冰与火之歌</name><author>乔治马丁</author><year>2014</year><price>89</price></book><book id='2'><name>安徒生童话</name><author>安徒生</author><year>2014</year><price>69</price></book></bookstore>";
//
        List<Sms> xml = XMLFactory.generate().getXML("<A>" + str0.replaceAll("<result>", "") + "</A>", new Sms());
        System.out.println(Arrays.asList(xml));
        System.out.println(xml.size());
        System.out.println(xml.get(0).toString());
        System.out.println("你好师姐!!!");

    }

    /**
     * 随机填充
     *
     * @Author:hihuzi 2018/6/23 9:28
     */
    public static <T> T getFillEntity(T t) throws Exception {

        Class<T> clazz = (Class<T>) t.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            String names = String.valueOf(field.getName());
            String value = String.valueOf(UUID.randomUUID().toString().substring(0, 3));
            StringBuffer name = new StringBuffer();
            name.append("set").append(names.substring(0, 1).toUpperCase()).append(names.substring(1));
            Class<?> declaredField = clazz.getDeclaredField(names).getType();
            Method method = clazz.getMethod(name.toString(), declaredField);
            if (!declaredField.getName().equals("java.util.List")) {
                Object invoke = method.invoke(t, value);
            }

        }
        return t;
    }

}