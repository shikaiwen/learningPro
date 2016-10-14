//package com.kevin.learning.netty;
//
//import org.jboss.netty.bootstrap.ServerBootstrap;
//import org.jboss.netty.channel.*;
//import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
//
//import java.net.InetSocketAddress;
//import java.util.concurrent.Executors;
//
///**
// * Created by Administrator on 2016/7/22.
// */
//public class HelloServer {
//
//    public static void main(String[] args) {
//
//        ServerBootstrap bootstrap = new ServerBootstrap(
//
//                new NioServerSocketChannelFactory(Executors.newSingleThreadExecutor(), Executors.newSingleThreadExecutor())
//
//        );
//
//
//        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
//            public ChannelPipeline getPipeline() throws Exception {
//                return Channels.pipeline(new HelloServerHandler());
//            }
//        });
//
//        bootstrap.bind(new InetSocketAddress(8888));
//    }
//
//}
//
//class HelloServerHandler extends SimpleChannelHandler{
//
//    @Override
//    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//
//        System.out.print("channel open");
//
//    }
//
//    @Override
//    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        System.out.print("connected ...");
//    }
//}
//
