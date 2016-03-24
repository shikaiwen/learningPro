package com.kevin.learning.jetty;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.InstanceManager;
import org.apache.tomcat.SimpleInstanceManager;
import org.eclipse.jetty.annotations.ServletContainerInitializersStarter;
import org.eclipse.jetty.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.jsp.JettyJspServlet;
import org.eclipse.jetty.plus.annotation.ContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 用多个配置文件运行多个 webApplication
 * 编译jsp
 * 
 * 完整示例:https://github.com/jetty-project/embedded-jetty-jsp
 * @author Administrator
 *
 */

public class MultiWebAppServer {

	public static void main(String[] args) throws Exception{
		test1() ;
	}
	
	public static void test1() throws Exception{
		
		Server server = new Server(20000);
		HandlerCollection handlers = new HandlerCollection();
		WebAppContext webapp1 = new WebAppContext();
//		org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
//		System.setProperty("org.apache.jasper.compiler.disablejsr199", "false");
		
		handlers.addHandler(webapp1);
		//资源放在当前class文件夹
		webapp1.setResourceBase(MultiWebAppServer.class.getResource("").toURI().toASCIIString());
		webapp1.setDefaultsDescriptor(MultiWebAppServer.class.getResource("webApp1.xml").toURI().toASCIIString());
		webapp1.setContextPath("/");
		
		webapp1.setAttribute("javax.servlet.context.tempdir", "D:/tmp/jsps");
//		webapp1.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/.*taglibs.*\\.jar$");
		webapp1.setAttribute("org.eclipse.jetty.containerInitializers", jspInitializer());
		webapp1.setAttribute(InstanceManager.class.getName(), new SimpleInstanceManager());
//		webapp1.addBean(new ServletContainerInitializersStarter(webapp1), true);
		ClassLoader jspClassLoader = new URLClassLoader(new URL[0], MultiWebAppServer.class.getClassLoader());
		webapp1.setClassLoader(jspClassLoader);
//		
//		webapp1.addServlet( jspServletHolder() , "*.jsp");
		
		handlers.addHandler(webapp1);
		
		server.setHandler(handlers);
		server.start();
		
		System.out.println("server started ");
		server.join();
	}
	
	
//	private static ServletHolder jspServletHolder(){
//		ServletHolder holderJsp = new ServletHolder("jsp",JettyJspServlet.class);
//		holderJsp.setInitOrder(0);
//		holderJsp.setInitParameter("logVerbosityLevel", "DEBUG");
//		holderJsp.setInitParameter("fork", "false");
//        holderJsp.setInitParameter("compilerTargetVM", "1.7");
//        holderJsp.setInitParameter("compilerSourceVM", "1.7");
//        holderJsp.setInitParameter("keepgenerated", "true");
//        return holderJsp;
//	}
	
	
	private static List<ContainerInitializer> jspInitializer(){
		JettyJasperInitializer sci = new JettyJasperInitializer();
		ContainerInitializer initializer = new ContainerInitializer(sci,null);
		List<ContainerInitializer> initializers = new ArrayList<ContainerInitializer>();
		initializers.add(initializer);
		return initializers;
	}
	
	
}
