package pojo;

public class ServerResponse {
    private String msgtype;
    private String code;
    private String responseMsg;

    public ServerResponse() {
    }

    public ServerResponse(String msgtype, String code, String responseMsg) {
        this.msgtype = msgtype;
        this.code = code;
        this.responseMsg = responseMsg;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public String getCode() {
        return code;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
