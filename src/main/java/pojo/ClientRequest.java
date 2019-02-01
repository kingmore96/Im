package pojo;

import java.util.ArrayList;
import java.util.List;

public class ClientRequest {
    private String msgType;
    private List<String> argsList = new ArrayList<String>();

    public ClientRequest(String msgType) {
        this.msgType = msgType;
    }

    public ClientRequest() {
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public List<String> getArgsList() {
        return argsList;
    }

    public void setArgsList(List<String> argsList) {
        this.argsList = argsList;
    }
}
