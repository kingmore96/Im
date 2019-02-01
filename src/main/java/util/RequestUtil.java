package util;

import pojo.ClientRequest;

public class RequestUtil {
    /**
     * 字符串转对象
     * @return
     */
    public static ClientRequest strToRequest(String str){
        if(str != null && str.length() > 0){
            String[] strings = str.split("%");
            if(strings != null && strings.length == 2){
                ClientRequest clientRequest = new ClientRequest();
                clientRequest.setMsgType(strings[0]);
                String[] strings1 = strings[1].split("\\|");
                if(strings1.length > 0) {
                    for (int i = 0; i < strings1.length; i++) {
                        clientRequest.getArgsList().add(strings1[i]);
                    }
                    return clientRequest;
                }
            }
        }
        return null;
    }
}
