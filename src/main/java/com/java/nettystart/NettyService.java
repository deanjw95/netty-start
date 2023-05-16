package com.java.nettystart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NettyService {

    private final ServerBootstrap serverBootstrap;

    public void startServer() throws InterruptedException {
        ChannelFuture f = serverBootstrap.bind(8080).sync();
        f.channel().closeFuture().sync();
    }

    @PostConstruct
    public void init() throws InterruptedException {
        startServer();
    }
}
