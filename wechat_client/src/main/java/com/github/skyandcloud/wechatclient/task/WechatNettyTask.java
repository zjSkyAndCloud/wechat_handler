package com.github.skyandcloud.wechatclient.task;

import com.github.skyandcloud.wechatclient.netty.handler.AcceptWechatMessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 微信服务任务
 * @Author zheng.jiang
 * @Date 2024/11/25 15:55
 */
@Slf4j
public class WechatNettyTask implements Runnable {

    /**
     * 微信服务IP
     */
    private final String wechatServerIp;

    /**
     * 微信服务端口
     */
    private final String wechatServerPort;

    public WechatNettyTask(String wechatServerIp, String wechatServerPort) {
        this.wechatServerIp = wechatServerIp;
        this.wechatServerPort = wechatServerPort;
    }

    @SneakyThrows
    @Override
    public void run() {
        log.info("accept wechat message server start");
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
                    channelPipeline.addLast(new DelimiterBasedFrameDecoder(1024 * 100, Delimiters.lineDelimiter()));
                    channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    channelPipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                    channelPipeline.addLast(new AcceptWechatMessageHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.bind(wechatServerIp, Integer.parseInt(wechatServerPort)).sync();
            log.info("netty server start success");
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
