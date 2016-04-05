package com.kevin.learning.basic.log;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;

public class MyHtmlLayout extends HTMLLayout{

	
	@Override
	public String format(LoggingEvent event) {
		Level level = event.getLevel();
		LocationInfo  locInfo = event.getLocationInformation();
		ThrowableInformation tInfo = event.getThrowableInformation();
		Throwable throwable = tInfo.getThrowable();
		return super.format(event);
	}


	public static void main(String[] args) {
		
	PatternLayout pl;
		
	}
	
	
	public static void groupTest(){
		List<String> list = new ArrayList<String>();
		for(int i = 0;i<2;i++){
			list.add(i+"");
		}
		
		List<List<String>> groupAll = new ArrayList<List<String>>();
		int count = 0;
		boolean jumpOut = false;
		
		int groupSize = 5;
		while(true){
			int fromIndex = count == 0 ? 0 : count * groupSize ; 
			int lastIndex = (count +1)*groupSize ;
			if(lastIndex >= list.size()){
				lastIndex = list.size();
				jumpOut = true;
			}
			groupAll.add( list.subList( fromIndex, lastIndex  ));
			count ++;
			if(jumpOut) break;
		}
		
		System.out.println("0000");
	}
}
