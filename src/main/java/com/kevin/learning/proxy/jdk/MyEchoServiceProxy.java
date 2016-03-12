package com.kevin.learning.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyEchoServiceProxy  implements InvocationHandler{

	EchoService service ;
	
	public MyEchoServiceProxy(EchoService service) {
		this.service = service;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//		Object str = method.invoke(proxy, args);
		System.out.println("before");
		Object result = method.invoke(service, args);
		System.out.println("after");
		return result;
	}

	
}
