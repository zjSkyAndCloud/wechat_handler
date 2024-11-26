package com.github.skyandcloud.wechatclient.task;

import com.github.skyandcloud.common.constant.NettyConstant;
import com.github.skyandcloud.common.dto.wechat.RegisterDto;
import com.github.skyandcloud.common.encoder.MessageDecoder;
import com.github.skyandcloud.common.encoder.MessageEncoder;
import com.github.skyandcloud.wechatclient.config.GlobalStaticConfig;
import com.github.skyandcloud.wechatclient.netty.handler.AcceptServerMessageHandler;
import com.github.skyandcloud.wechatclient.netty.handler.AcceptServerRegisterConfigHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 客户端netty任务
 * @Author zheng.jiang
 * @Date 2024/11/26 16:00
 */
@Slf4j
public class ClientNettyTask implements Runnable {

    /**
     * 当前节点权重
     */
    private Integer nodeWeight;

    /**
     * 服务端IP
     */
    private String serverNettyIp;

    /**
     * 服务端端口
     */
    private Integer serverNettyPort;

    private Channel channel;

    public ClientNettyTask(Integer nodeWeight, String serverNettyIp, Integer serverNettyPort) {
        this.nodeWeight = nodeWeight;
        this.serverNettyIp = serverNettyIp;
        this.serverNettyPort = serverNettyPort;
    }

    @SneakyThrows
    @Override
    public void run() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline channelPipeline = socketChannel.pipeline();
                    channelPipeline.addLast(new DelimiterBasedFrameDecoder(8192, Unpooled.copiedBuffer(NettyConstant.DELIMITER)));
                    channelPipeline.addLast(new MessageDecoder());
                    channelPipeline.addLast(new MessageEncoder());
                    channelPipeline.addLast(new AcceptServerMessageHandler());
                    channelPipeline.addLast(new AcceptServerRegisterConfigHandler());
                }
            });


            ChannelFuture channelFuture = bootstrap.connect(serverNettyIp, serverNettyPort).sync();
            log.info("netty client start success");
            channel = channelFuture.channel();
            sendRegisterMessage();
            channel.closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 发送注册成功消息
     */
    private void sendRegisterMessage() {
        RegisterDto registerDo = new RegisterDto(GlobalStaticConfig.getName(), GlobalStaticConfig.getWxid(), nodeWeight);
        sendMessage(registerDo);
    }

    /**
     * 发送消息到服务器
     *
     * @param msg
     */
    public synchronized void sendMessage(Object msg) {
        channel.writeAndFlush(msg).addListener(new LoggingListener());
    }

    //日志监听类
    static class LoggingListener implements ChannelFutureListener {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            if (!future.isSuccess()) {
                Throwable cause = future.cause();
                log.error(cause.getMessage(), cause);
            }
        }
    }
}
