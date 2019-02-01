package util;

import pojo.ServerResponse;

public class ResponseUtil {

    /**
     * 根据响应构造响应字符串
     * @param response
     * @return
     */
    public static String serverResponseToStr(ServerResponse response){
        if(response != null){
            if(response.getMsgtype() != null && response.getMsgtype().length()>0){
                if(response.getCode() != null && response.getCode().length() >0){
                    if(response.getResponseMsg() != null && response.getMsgtype().length() > 0){
                        StringBuilder sb = new StringBuilder();
                        sb.append(response.getMsgtype());
                        sb.append("%");
                        sb.append(response.getCode());
                        sb.append("|");
                        sb.append(response.getResponseMsg());
                        return sb.toString();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 根据响应字符串构造响应对象
     * @param str
     * @return
     */
    public static ServerResponse strToServerResponse(String str){
        if(str != null && str.length() > 0){
            String[] strings = str.split("%");
            if(strings != null && strings.length == 2){
                ServerResponse serverResponse = new ServerResponse();
                serverResponse.setMsgtype(strings[0]);
                String[] strings1 = strings[1].split("\\|");
                if(strings1 != null && strings1.length == 2){
                    serverResponse.setCode(strings1[0]);
                    serverResponse.setResponseMsg(strings1[1]);
                    return serverResponse;
                }
            }
        }
        return null;
    }
}
