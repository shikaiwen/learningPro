//package com.kevin.learning.netty;
//
//import org.jboss.netty.bootstrap.ClientBootstrap;
//import org.jboss.netty.channel.*;
//import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
//import org.jboss.netty.handler.codec.serialization.ObjectEncoder;
//
//import java.net.InetSocketAddress;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
///**
// * Created by Administrator on 2016/7/25.
// */
//public class MyClient1 {
//    public static void main(String[] args) {
//
//
//        Executor bossPool = Executors.newCachedThreadPool();
//        Executor workerPool = Executors.newCachedThreadPool();
//
//        ChannelFactory channelFactory = new NioClientSocketChannelFactory(bossPool, workerPool);
//
//
//        ChannelPipelineFactory pipelineFactory = new ChannelPipelineFactory() {
//            @Override
//            public ChannelPipeline getPipeline() throws Exception {
//
//                return Channels.pipeline(new ObjectEncoder());
//            }
//        };
//
//        ClientBootstrap bootstrap = new ClientBootstrap(channelFactory);
//        bootstrap.setPipelineFactory(pipelineFactory);
//
//        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8888);
//
//
//        ChannelFuture cf = bootstrap.connect(addr);
//        cf.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture channelFuture) throws Exception {
//
//            }
//        });
//
//    }
//}
