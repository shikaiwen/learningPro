package com.kevin.learning.basic.jdk.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.HexDump;
import org.junit.Test;

public class StringTest {

	@Test
	public void dump() throws Exception{
		File classFile = new File("D:/mypro/learningCode/target/classes/com/kevin/learning/basic/jdk/test/TestClass.class");
		byte[] data = FileUtils.readFileToByteArray(classFile);
		
		HexDump.dump(data, 0, new FileOutputStream(classFile.getParent() +"/dump.txt"), 0);
	}
	
	public static void main(String[] args) throws Exception, FileNotFoundException {
		String url = StringTest.class.getResource("test.txt").getPath();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(url),"utf8"));
		
		String str = new String(FileUtils.readFileToByteArray( new File(url)), "utf8" );
		String result = str.replaceAll("[\\s]*", "");
		
		System.out.println(result);
	}
	
}
