package xml.core;


import xml.XMLFactory;
import xml.constant.XMLConstant;

import java.util.List;

/**
 * TIPS XML 工具类
 *
 * @Author:hihuzi 2018/7/19 11:01
 */

public class XMLTool extends XMLToolImpl implements XMLFactory {

    /**
     * tips XML 转换成 String 生成器
     *
     * @Author:hihuzi 2018/6/20 8:59
     */
    @Override
    public String getXML(List list) throws Exception {

        StringBuffer XML = new StringBuffer();
        XML.append(XMLConstant.Title.getValue());
        return getXml(list, XMLConstant.SIGN.getValue(), XML);
    }

    /**
     * tips XML 转换成 对象 解析器
     * 处理规则xml
     *
     * @param XML
     * @param t
     * @Explanation: 必须有结束标签
     * @Author:hihuzi 2018/6/20 8:59
     */
    @Override
    public <T> List<T> getXML(String XML, T t) throws Exception {

        return getXml(XML, t, XMLConstant.Title);
    }

}
