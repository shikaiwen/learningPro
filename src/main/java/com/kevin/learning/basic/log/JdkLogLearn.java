package com.kevin.learning.basic.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JdkLogLearn {

	
	private static final Logger logger=Logger.getLogger(JdkLogLearn.class.getName());
	
	ConsoleHandler cH;
	FileHandler fH;
	
	public static void main(String[] args) {
//		String cname = System.getProperty("java.util.logging.manager");
//		String fname = System.getProperty("java.util.logging.config.file");
//		System.out.println(fname );
		Logger.getGlobal().setLevel(Level.ALL);
		logger.log(Level.ALL, "2323");
		logger.log(Level.WARNING, JdkLogLearn.class.getName());
	}
}
