package xml.constant;

/**
 * @Features: 配置头和 固定变量 特定需求
 * @Date:
 * @Author: hihuzi  2018/6/20 8:11
 */

public enum XMLConstant {
    Title(1, "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"), SIGN(2, "_v"),
    Date(1, "YYYY-MM-DD");

    Integer sign;

    String value;

    XMLConstant(Integer sign, String value) {

        this.sign = sign;
        this.value = value;
    }


    public String getValue() {

        return value;
    }

    public XMLConstant setValue(String value) {

        this.value = value;
        return this;
    }
}