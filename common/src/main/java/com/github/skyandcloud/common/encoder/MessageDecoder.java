package com.github.skyandcloud.common.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.springframework.util.SerializationUtils;

import java.util.List;

/**
 * @Description 消息解码器
 * @Author zheng.jiang
 * @Date 2024/9/4 12:53
 */
public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) {
        final int length = byteBuf.readableBytes();
        byte[] bytes = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), bytes, 0, length);
        list.add(SerializationUtils.deserialize(bytes));
    }
}
