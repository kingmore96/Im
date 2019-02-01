package util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;

public class EncodeUtil {

    /**
     * 编码方法
     * @param req
     * @return
     */
    public static ByteBuf encode(String req){
        ByteBuf encode = Base64.encode(Unpooled.buffer().writeBytes(req.getBytes()));
        ByteBuf byteBuf = Unpooled.copiedBuffer(encode);
        byteBuf.writeBytes(")".getBytes());
        return byteBuf;
    }
}