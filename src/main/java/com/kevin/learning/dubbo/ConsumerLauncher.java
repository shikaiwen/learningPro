package com.kevin.learning.dubbo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerLauncher {

	public static void main(String[] args) {
		String curClassFileDir = ConsumerLauncher.class.getResource("").toExternalForm();
		String springFileName = "applicationContext-consumer.xml";
		String springFilePath = curClassFileDir + springFileName ;
		
		ApplicationContext context = new ClassPathXmlApplicationContext(springFilePath);
		HelloService service = context.getBean(HelloService.class);
		
		String responseStr = service.hello("kevin");
		System.out.println("responseStr:" + responseStr);
	}
}
