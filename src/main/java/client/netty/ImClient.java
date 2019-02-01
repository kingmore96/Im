package client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.base64.Base64Decoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.concurrent.TimeUnit;

/**
 * netty客户端
 */
public class ImClient {
    private static int count = 1;

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        final Bootstrap b = new Bootstrap();
        b.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        ByteBuf delimiter = Unpooled.buffer().writeBytes(")".getBytes());
                        //解码
                        pipeline.addLast(new DelimiterBasedFrameDecoder(1024,true,true,delimiter));
                        pipeline.addLast(new Base64Decoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new ImClientHandlerForLogin());
                    }
                });
        doTcpConnect(b,"127.0.0.1",80);
    }

    private static void doTcpConnect(final Bootstrap bootstrap, final String host, final int port){
        bootstrap.connect(host,port).addListener(new ChannelFutureListener() {
            public void operationComplete(final ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    System.out.println("连接成功");
                }else if(count == 5){
                    System.out.println("重连达到五次，不再重连");
                }else{
                    channelFuture.channel().eventLoop().parent().schedule(new Runnable() {
                        public void run() {
                            doTcpConnect(bootstrap,host,port);
                        }
                    }, 60, TimeUnit.SECONDS);
                }
            }
        });
        count++;
    }
}
