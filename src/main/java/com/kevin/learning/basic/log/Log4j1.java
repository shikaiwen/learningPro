package com.kevin.learning.basic.log;

import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * 参考文章：http://tianweili.github.io/blog/2015/03/03/log4j/
 * 学习知识点：用properties文件声明的 rootLogger格式为 loglevel , appender1,appender2,appender3,rootLogger的日志级别和
 * appender是所有子logger都会继承的（如果自己没有声明appender的话）
 * 
 * @author Administrator
 *
 */

public class Log4j1 {

	static Logger logger = Logger.getLogger(Log4j1.class);
	
	Category category;
	Logger log;
	Appender appender;
	PatternLayout lp;
	Level level;
	
	ConsoleAppender stdout;
	DailyRollingFileAppender dr;
	
	public static void main(String[] args) {
		
		logger.debug("start main process ...");
		logger.debug(" compute salary ");
		
//		logger.error(" error when compute salary ");
		try{
			int a = 100 / 0;
		}catch(Exception e){
			logger.error("计算薪资出错",e);
		}
		
	}
	
}
