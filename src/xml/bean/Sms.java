package xml.bean;

import xml.annotation.XMLClosure;
import xml.annotation.XMLEntity;

/**
 * @Features:
 * @Date:
 * @Author: hihuzi  2018/6/26 21:02
 */
@XMLEntity(isHavingEndTag = true)
public class Sms {

    @XMLClosure(name = "Result", isHavingEndTag = true, attributes = {"id", "cc"})
    private String result;

    @XMLClosure(name = "id")
    private String id;

    private String cc;

    @XMLClosure(name = "Mobile")
    private String mobile;

    @XMLClosure(name = "exec_time")
    private String execTime;

    @XMLClosure(name = "Message")
    private String message;

    private String state;

    @XMLClosure(name = "user_task_id")
    private String userTaskId;

    public String getResult() {

        return result;
    }

    public void setResult(String result) {

        this.result = result;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getExecTime() {

        return execTime;
    }

    public void setExecTime(String execTime) {

        this.execTime = execTime;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public String getUserTaskId() {

        return userTaskId;
    }

    public void setUserTaskId(String userTaskId) {

        this.userTaskId = userTaskId;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getCc() {

        return cc;
    }

    public void setCc(String cc) {

        this.cc = cc;
    }

    @Override
    public String toString() {

        return "Sms{" +
                "result='" + result + '\'' +
                ", id='" + id + '\'' +
                ", cc='" + cc + '\'' +
                ", mobile='" + mobile + '\'' +
                ", execTime='" + execTime + '\'' +
                ", message='" + message + '\'' +
                ", state='" + state + '\'' +
                ", userTaskId='" + userTaskId + '\'' +
                '}';
    }

}