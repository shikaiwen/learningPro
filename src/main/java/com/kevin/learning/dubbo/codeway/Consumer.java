package com.kevin.learning.dubbo.codeway;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.kevin.learning.dubbo.HelloService;

public class Consumer {

	public static void main(String[] args) {
		ApplicationConfig application = new ApplicationConfig();
		application.setName("dubboClient");
		
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("multicast://224.5.6.7:20046");
//		registry.setProtocol(protocol);
		registry.setUsername("aaa");
		registry.setPassword("bbb");
		
		ReferenceConfig<HelloService> reference = new ReferenceConfig<HelloService>();
		reference.setApplication(application);
		reference.setRegistry(registry);
		reference.setInterface(HelloService.class);
		reference.setVersion("1.0.0");
		
		HelloService s = reference.get();
		String result = s.hello("马大帅");
		System.out.println("result:"+ result);
	}
}
