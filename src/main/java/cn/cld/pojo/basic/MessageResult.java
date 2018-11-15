package cn.cld.pojo.basic;

public class MessageResult {
    private Boolean result;
    private String message;
    private String remarks;


    public MessageResult() {
        //默认处理成功
        result = true;
    }

    public MessageResult(Boolean result, String message, String remarks) {
        this.result = result;
        this.message = message;
        this.remarks = remarks;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
