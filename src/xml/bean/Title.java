package xml.bean;


import xml.annotation.XMLClosure;
import xml.annotation.XMLEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * @Features:
 * @Date:
 * @Author: hihuzi  2018/6/18 9:59
 */
@XMLEntity(isHavingEndTag = true)
public class Title {

    @XMLClosure(name = "ABC", isHavingEndTag = true, attributes = {"iscqueue", "queuecode", "statues"})
    private String dataRecordSet;

    private String iscqueue = "N";

    private String queuecode = "12312";

    private String statues = "1";

    @XMLClosure(name = "DataRecord", isHavingEndTag = true, attributes = {"table"})
    private String dataRecord;

    private String table = "ts_upload_batch";

    private String batchno;

    private String uploadbranch;

    private String uploadbranchname;

    private String ownerbranch;

    private List<Body> bodys = new ArrayList<>();

    public String getDataRecordSet() {

        return dataRecordSet;
    }

    public void setDataRecordSet(String dataRecordSet) {

        this.dataRecordSet = dataRecordSet;
    }

    public String getIscqueue() {

        return iscqueue;
    }

    public void setIscqueue(String iscqueue) {

        this.iscqueue = iscqueue;
    }

    public String getQueuecode() {

        return queuecode;
    }

    public void setQueuecode(String queuecode) {

        this.queuecode = queuecode;
    }

    public String getStatues() {

        return statues;
    }

    public void setStatues(String statues) {

        this.statues = statues;
    }

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

    public String getBatchno() {

        return batchno;
    }

    public void setBatchno(String batchno) {

        this.batchno = batchno;
    }

    public String getUploadbranch() {

        return uploadbranch;
    }

    public void setUploadbranch(String uploadbranch) {

        this.uploadbranch = uploadbranch;
    }

    public String getUploadbranchname() {

        return uploadbranchname;
    }

    public void setUploadbranchname(String uploadbranchname) {

        this.uploadbranchname = uploadbranchname;
    }

    public String getOwnerbranch() {

        return ownerbranch;
    }

    public void setOwnerbranch(String ownerbranch) {

        this.ownerbranch = ownerbranch;
    }

    public List<Body> getBodys() {

        return bodys;
    }

    public void setBodys(List<Body> bodys) {

        this.bodys = bodys;
    }

    public void addBodys(Body body) {

        this.bodys.add(body);
    }

}
