package com.java.nettystart.client;

import com.java.nettystart.domain.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 클라이언트는 역방향 인코딩 및 디코딩을 수행해야 하므로 RequestDataEncoder 및 ResponseDataDecoder가 필요합니다.
 */
public class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ResponseData data = new ResponseData();
        data.setIntValue(in.readInt());
        out.add(data);
    }
}
