package com.github.skyandcloud.common.encoder;

import com.github.skyandcloud.common.constant.NettyConstant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.util.SerializationUtils;

/**
 * @Description 消息编码器
 * @Author zheng.jiang
 * @Date 2024/9/4 12:53
 */
public class MessageEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object object, ByteBuf byteBuf) {
        byte[] bytes = SerializationUtils.serialize(object);
        byteBuf.writeBytes(bytes);
        byteBuf.writeBytes(NettyConstant.DELIMITER);
    }
}
