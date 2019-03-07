package cn.cld.pojo;

import java.util.Date;

public class Logs {
    private Integer id;

    private Integer operator;

    private String operatorType;

    private String operatorObject;

    private Integer operatorTrans;

    private Date time;

    private Integer dataCount;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType == null ? null : operatorType.trim();
    }

    public String getOperatorObject() {
        return operatorObject;
    }

    public void setOperatorObject(String operatorObject) {
        this.operatorObject = operatorObject == null ? null : operatorObject.trim();
    }

    public Integer getOperatorTrans() {
        return operatorTrans;
    }

    public void setOperatorTrans(Integer operatorTrans) {
        this.operatorTrans = operatorTrans;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}