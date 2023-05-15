package com.java.nettystart.decoder;

import com.java.nettystart.domain.RequestData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Netty는 ChannelInboundHandler의 구현인 유용한 디코더 클래스인 ByteToMessageDecoder 및 ReplayingDecoder를 제공합니다.
 */
public class RequestDecoder extends ReplayingDecoder<RequestData> {

    private final Charset charset = StandardCharsets.UTF_8;

    /**
     * Netty로 채널 처리 파이프라인을 만들 수 있습니다.
     * 따라서 디코더를 첫 번째 핸들러로 놓고 처리 로직 핸들러가 그 뒤에 올 수 있습니다.
     * 버퍼에 읽기 작업을 위한 데이터가 충분하지 않을 때 예외를 발생시키는 ByteBuf 구현을 사용합니다.
     * 예외가 포착되면 버퍼가 처음으로 되감겨지고 디코더는 데이터의 새로운 부분을 기다립니다.
     * 디코딩 실행 후 out 목록이 비어 있지 않으면 디코딩이 중지됩니다.
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        RequestData data = new RequestData();
        data.setIntValue(in.readInt());
        int strLen = in.readInt();
        data.setStringValue(in.readCharSequence(strLen, charset).toString());
        out.add(data);
    }
}
