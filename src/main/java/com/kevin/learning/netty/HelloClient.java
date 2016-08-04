package com.kevin.learning.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/7/22.
 */
public class HelloClient {

    public static void main(String[] args) {
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()
                )
        );

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {

                return Channels.pipeline(new HelloClientHandler());
            }
        });

        bootstrap.connect(new InetSocketAddress("127.0.0.1", 8888));
    }

}


class HelloClientHandler extends SimpleChannelHandler{

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.print("client connected ...");
    }
}
