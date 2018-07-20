package xml;

import xml.core.XMLTool;

import java.util.List;

/**
 * tips 基于注解解析xml 和 生成器
 *
 * @Features: XML 生成构建器 解析器
 * @Date:
 * @Author: hihuzi  2018/6/20 8:05
 */
public interface XMLFactory {

    XMLTool XMLtool = null;

    static XMLTool generate() {

        if (XMLtool == null)
            return new XMLTool();
        return XMLtool;
    }

    /**
     * tips XML 转换成 String 生成器
     *
     * @Author:hihuzi 2018/6/20 8:59
     */
    String getXML(List list) throws Exception;

    /**
     * tips XML 转换成 对象 解析器
     * 处理规则xml
     *
     * @Explanation: 必须有结束标签
     * @Author:hihuzi 2018/6/20 8:59
     */
    <T> List<T> getXML(String XML, T t) throws Exception;

}
