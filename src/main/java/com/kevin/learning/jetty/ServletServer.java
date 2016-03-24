package com.kevin.learning.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * reference site :https://examples.javacodegeeks.com/enterprise-java/jetty/embedding-jetty-with-servlet/
 * there are lots of jetty related articles : https://examples.javacodegeeks.com/category/enterprise-java/jetty/
 * 
 * @WebServlet usage : http://stackoverflow.com/questions/6535676/webservlet-annotation-with-tomcat-7
 * @author kevin
 *
 */

public class ServletServer {

	public static void main(String[] args) throws Exception{
		test1();
	}
	
	public static void test1() throws Exception{
		Server server = new Server(26636);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		ServletHolder holder = new ServletHolder();
		holder.setServlet(new HelloServlet());
		context.addServlet( holder , "/hello");
		
		server.setHandler(context);
		server.start();
	}
}

class HelloServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("hello world");
	}
}
