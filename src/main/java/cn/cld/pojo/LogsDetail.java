package cn.cld.pojo;

import java.util.Date;

public class LogsDetail {
    private Integer id;

    private Integer logsId;

    private Integer operator;

    private Date operatorTime;

    private Integer operatorTrans;

    private String message;

    private String remark;

    private String undefined1;

    private String undefined2;

    private String undefined3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLogsId() {
        return logsId;
    }

    public void setLogsId(Integer logsId) {
        this.logsId = logsId;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Integer getOperatorTrans() {
        return operatorTrans;
    }

    public void setOperatorTrans(Integer operatorTrans) {
        this.operatorTrans = operatorTrans;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUndefined1() {
        return undefined1;
    }

    public void setUndefined1(String undefined1) {
        this.undefined1 = undefined1 == null ? null : undefined1.trim();
    }

    public String getUndefined2() {
        return undefined2;
    }

    public void setUndefined2(String undefined2) {
        this.undefined2 = undefined2 == null ? null : undefined2.trim();
    }

    public String getUndefined3() {
        return undefined3;
    }

    public void setUndefined3(String undefined3) {
        this.undefined3 = undefined3 == null ? null : undefined3.trim();
    }
}