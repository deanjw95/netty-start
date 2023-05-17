package com.java.nettystart;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServerStartupListener {

    private final NettyService service;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) throws InterruptedException {
        // 스프링 컨텍스트가 초기화될 때 실행되는 로직
        service.startServer();
    }
}
