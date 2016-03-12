package com.kevin.learning.proxy.jdk;

import java.lang.reflect.InvocationHandler;

public class EchoServiceImpl implements EchoService{

	

	public String hello(String name) {
		
		return "hello " + name;
	}

}
