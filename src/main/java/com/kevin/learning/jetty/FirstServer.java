package com.kevin.learning.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * 这个样配置是只能支持静态文件的
 * @author Administrator
 *
 */

public class FirstServer {

	public static void main(String[] args) throws Exception{
		test1();
	}
	
	public static void test1() throws Exception{
		Server server = new Server(8888);
		ResourceHandler resHandler = new ResourceHandler();
		resHandler.setResourceBase("D:/");
		resHandler.setDirectoriesListed(true);
		server.setHandler(resHandler);
		server.start();
	}
}
