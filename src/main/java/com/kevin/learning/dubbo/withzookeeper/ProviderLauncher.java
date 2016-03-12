package com.kevin.learning.dubbo.withzookeeper;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderLauncher {

	public static void main(String[] args) throws Exception {
		String curDir = ProviderLauncher.class.getResource("").toExternalForm();
		String configFilePath = curDir + "applicationContext-provider.xml";
//		System.out.println(curDir);
		ApplicationContext context = new ClassPathXmlApplicationContext(configFilePath);
		
		System.in.read();
	}
}
