package com.kevin.learning.dubbo;

public class HelloServiceImpl implements HelloService {

	public String hello(String name) {
		
		return "hello " + name;
	}

}
