package server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import pojo.ClientRequest;
import pojo.ServerResponse;
import server.service.LoginService;
import server.service.LoginServiceImpl;
import util.EncodeUtil;
import util.RequestUtil;
import util.ResponseUtil;

public class ImServerHandlerForLogin extends SimpleChannelInboundHandler<String> {
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("读取到客户端数据"+msg);
        ClientRequest clientRequest = RequestUtil.strToRequest(msg);

        //登录请求，处理
        if(clientRequest != null && "LI".equals(clientRequest.getMsgType())) {
            LoginService loginService = new LoginServiceImpl();

            String username = clientRequest.getArgsList().get(0);
            String passwd = clientRequest.getArgsList().get(1);

            ServerResponse serverResponse = loginService.loginOrRegister(username, passwd);
            String s = ResponseUtil.serverResponseToStr(serverResponse);
            System.out.println("构造响应" + s);

            if (s != null) {
                ByteBuf encode = EncodeUtil.encode(s);
                ctx.writeAndFlush(encode);
                System.out.println("发送登录响应信息");
            }
        }else if(clientRequest != null && !"LI".equals(clientRequest.getMsgType())){
            System.out.println("不是登录请求，进入下一个handler");
            ctx.fireChannelRead(msg);
        }else{
            System.out.println("错误请求格式"+msg);
        }
    }
}
