package client.netty;

import client.console.ReadClient;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pojo.ServerResponse;
import util.EncodeUtil;
import util.ResponseUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImClientHandlerForLogin extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //登录逻辑
        System.out.println("开始登录");
        loginAndWrite(ctx);
    }

    private void loginAndWrite(ChannelHandlerContext ctx) throws IOException {
        String result = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("请输入用户名和密码，中间用一个|分割:");
            result = br.readLine();
        }while(!validateLogin(result));

        //构造指令字符串
        String req = "LI"+"%"+result.split("\\|")[0]+"|"+result.split("\\|")[1];
        ByteBuf byteBuf = EncodeUtil.encode(req);
        ctx.writeAndFlush(byteBuf);
        System.out.println("发送客户端指令完成");
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("响应到服务端指令"+s);
        //解析字符串
        ServerResponse serverResponse = ResponseUtil.strToServerResponse(s);
        //如果是登录响应，处理
        if(serverResponse != null && "LI".equals(serverResponse.getMsgtype())){
            if("200".equals(serverResponse.getCode())){
                System.out.println(serverResponse.getResponseMsg());
                new Thread(new ReadClient()).start();
            }else if("400".equals(serverResponse.getCode())){
                System.out.println(serverResponse.getResponseMsg());
                loginAndWrite(channelHandlerContext);
            }else {
                System.out.println("未知错误");
                channelHandlerContext.close();
            }
        }else if(serverResponse != null && !"LI".equals(serverResponse.getMsgtype())){
            System.out.println("不是登录响应，进入下一个handler");
            channelHandlerContext.fireChannelRead(s);
        }else{
            System.out.println("响应格式错误"+s);
        }
    }

    private boolean validateLogin(String s) {
        if(s != null && s.length() > 0){
            String[] strings = s.split("\\|");
            if(strings.length == 2){
                return validateUsername(strings[0]) && validatePasswd(strings[1]);
            }
            return false;
        }
        return false;
    }

    private boolean validatePasswd(String passwd) {
       if(passwd != null && passwd.length() > 0){
           if(passwd.length() >=8 && passwd.length() <= 16){
               return passwd.matches("^(?=.*[a-z])(?=.*[0-9])(.{8,16})$");
           }
           return false;
       }
       return false;
    }

    private boolean validateUsername(String username) {
        if(username!= null && username.length() > 0 ){
            if(username.length() < 10){
                return username.matches("^[a-z]+[a-z0-9]*$");
            }
            return false;
        }
        return false;
    }


}
