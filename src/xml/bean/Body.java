package xml.bean;


import xml.annotation.XMLClosure;
import xml.annotation.XMLEntity;

/**
 * @Features:
 * @Date:
 * @Author: hihuzi  2018/6/18 9:49
 */
@XMLEntity(isHavingEndTag = true)
public class Body {

    @XMLClosure(name = "CBD", isHavingEndTag = true, attributes = {"table", "plateno"})
    private String dataRecord;

    private String table = "ts_upload_data";

    private String plateno;

    private String cname;

    private String mobile1;

    public String getDataRecord() {

        return dataRecord;
    }

    public void setDataRecord(String dataRecord) {

        this.dataRecord = dataRecord;
    }

    public String getTable() {

        return table;
    }

    public void setTable(String table) {

        this.table = table;
    }

    public String getPlateno() {

        return plateno;
    }

    public void setPlateno(String plateno) {

        this.plateno = plateno;
    }

    public String getCname() {

        return cname;
    }

    public void setCname(String cname) {

        this.cname = cname;
    }

    public String getMobile1() {

        return mobile1;
    }

    public void setMobile1(String mobile1) {

        this.mobile1 = mobile1;
    }

}
