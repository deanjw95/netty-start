package com.java.nettystart;

import com.java.nettystart.decoder.RequestDecoder;
import com.java.nettystart.encoder.ResponseDataEncoder;
import com.java.nettystart.handler.ProcessingHandler;
import com.java.nettystart.handler.SimpleProcessingHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyConfig {

    @Bean
    public EventLoopGroup bossGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public EventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public ServerBootstrap serverBootstrap() {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup(), workerGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    // 올바른 순서로 요청 및 출력을 처리할 인바운드 및 아웃바운드 핸들러를 정의합니다.
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new RequestDecoder(), new ResponseDataEncoder(), new ProcessingHandler());
                    }
                }).option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        return b;
    }
}
