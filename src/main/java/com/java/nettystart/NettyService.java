package com.java.nettystart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NettyService {

    private final ServerBootstrap serverBootstrap;
    private ChannelFuture f;

    public void startServer() throws InterruptedException {
        f = serverBootstrap.bind(8080).sync();
        f.channel().closeFuture().sync();
    }

    // Bean을 제거하기 전에 해야할 작업이 있을 때 설정
    @PreDestroy
    public void stopServer() {
        if (f != null) {
            f.channel().close();
            f.channel().closeFuture();
        }
    }
}
