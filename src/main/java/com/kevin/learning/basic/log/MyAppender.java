package com.kevin.learning.basic.log;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class MyAppender extends AppenderSkeleton{

	@Override
	protected void append(LoggingEvent event) {
		System.out.println(" from myappender : " + event.getMessage());
	}

	@Override
	public void close() {
		
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

}
