package com.kevin.learning.basic.jdk.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;

public class StringTest {

	public static void main(String[] args) throws Exception, FileNotFoundException {
		String url = StringTest.class.getResource("test.txt").getPath();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(url),"utf8"));
		
		String str = new String(FileUtils.readFileToByteArray( new File(url)), "utf8" );
//		String str = "134	 "
//				+ " "
//				+ ""
//				+ ""
//				+ ""
//				+ ""
//				+ "\n \r  \n\r ab cd dnn \t";
		String result = str.replaceAll("[\\s]*", "");
		System.out.println(result);
	}
	
}
