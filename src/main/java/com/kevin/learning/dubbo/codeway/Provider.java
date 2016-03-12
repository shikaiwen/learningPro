package com.kevin.learning.dubbo.codeway;

import java.io.IOException;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.kevin.learning.dubbo.HelloService;
import com.kevin.learning.dubbo.HelloServiceImpl;

public class Provider {

	public static void main(String[] args) throws Exception {
		HelloService service = new HelloServiceImpl();
		
		// app config
		ApplicationConfig application = new ApplicationConfig();
		application.setName("dubboProvider");
		
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("multicast://224.5.6.7:20046");
		registry.setUsername("aaa");
		registry.setPassword("bbb");
		
		//protocol config
		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		protocol.setPort(12345);
		protocol.setThreads(200);
		
		//service provider 
		ServiceConfig<HelloService> serviceConfig = new ServiceConfig<HelloService>();
		serviceConfig.setApplication(application);
		serviceConfig.setRegistry(registry);
		serviceConfig.setProtocol(protocol);
		serviceConfig.setInterface(HelloService.class);
		serviceConfig.setRef(service);
		serviceConfig.setVersion("1.0.0");
		
		serviceConfig.export();
		
		System.in.read();
	}
}
