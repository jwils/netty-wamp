package io.netty.protocol.wamp;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.protocol.wamp.server.WampServer;

public class WampServerInitializer extends ChannelInitializer<SocketChannel> {

	private final WampServer wampServer;
	private final ObjectMapper objectMapper;

	public WampServerInitializer(WampServer wampServer, ObjectMapper objectMapper) {
		this.wampServer = wampServer;
		this.objectMapper = objectMapper;
	}

	@Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("codec-http", new HttpServerCodec());
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        pipeline.addLast("handler-ws", new WebSocketServerHandler());
        pipeline.addLast("codec-wamp", new WampMessageCodec());
        pipeline.addLast("handler-wamp", new WampServerHandler(wampServer, objectMapper));
    }
}
