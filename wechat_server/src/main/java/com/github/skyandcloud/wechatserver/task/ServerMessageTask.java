package com.github.skyandcloud.wechatserver.task;

import com.github.skyandcloud.common.constant.NettyConstant;
import com.github.skyandcloud.common.encoder.MessageDecoder;
import com.github.skyandcloud.common.encoder.MessageEncoder;
import com.github.skyandcloud.wechatserver.netty.handler.ServerMessageHandler;
import com.github.skyandcloud.wechatserver.netty.handler.ServerStatusHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 服务器接受消息任务
 * @Author zheng.jiang
 * @Date 2024/11/28 15:19
 */
@Slf4j
public class ServerMessageTask implements Runnable {

    /**
     * 服务器netty端口
     */
    private final Integer nettyServerPort;

    /**
     * 处置消息handler
     */
    private final ServerMessageHandler serverMessageHandler;

    /**
     * 处置注册消息handler
     */
    private final ServerStatusHandler serverStatusHandler;

    public ServerMessageTask(Integer nettyServerPort, ServerMessageHandler serverMessageHandler, ServerStatusHandler serverStatusHandler) {
        this.nettyServerPort = nettyServerPort;
        this.serverMessageHandler = serverMessageHandler;
        this.serverStatusHandler = serverStatusHandler;
    }

    @Override
    public void run() {
        log.info("server accept message task start");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline channelPipeline = socketChannel.pipeline();
                    channelPipeline.addLast(new DelimiterBasedFrameDecoder(819200, Unpooled.copiedBuffer(NettyConstant.DELIMITER)));
                    channelPipeline.addLast(new MessageDecoder());
                    channelPipeline.addLast(new MessageEncoder());
                    channelPipeline.addLast(serverMessageHandler);
                    channelPipeline.addLast(serverStatusHandler);
                }
            });
            ChannelFuture channelFuture = bootstrap.bind(nettyServerPort).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
