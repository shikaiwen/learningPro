package com.kevin.learning.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class Main {

	public static void main(String[] args) throws Exception {
		
		
		test2();
		
	
	}
	
	public static void test2() throws Exception{
		
		EchoService service = new EchoServiceImpl();
		MyEchoServiceProxy p = new MyEchoServiceProxy(service);
//		new Class<>[]{EchoService.class}
		
		Class pClass = Proxy.getProxyClass(service.getClass().getClassLoader(), EchoService.class);
		
		EchoService proxy = (EchoService)pClass.getConstructor(new Class[]{InvocationHandler.class})
				.newInstance( new Object[] { p }  );
		
		String result = proxy.hello("kevin");
		
	}
	
	
	public static void test1(){
		EchoService service = new EchoServiceImpl();
		MyEchoServiceProxy p = new MyEchoServiceProxy(service);
		
		EchoService m = (EchoService)Proxy.newProxyInstance(service.getClass().getClassLoader(),
				service.getClass().getInterfaces(),  p );
		
		System.out.println( m instanceof Proxy );
		String str = m.hello("kevin");
		System.out.println("main result : " + str);
	}
}
