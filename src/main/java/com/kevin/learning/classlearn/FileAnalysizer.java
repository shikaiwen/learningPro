package com.kevin.learning.classlearn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.HexDump;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.util.Assert;


class CONSTANT_Class_info{
	byte tag ;
	short index;
}




public class FileAnalysizer {

	
	
	
//	static Logger logger = Logger.getLogger(FileAnalysizer.class);
	
	public static void main(String[] args) throws Exception {
		String classpath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		String [] filepartArr = FileAnalysizer.class.getCanonicalName().split("\\.");
		String fullpath = classpath + StringUtils.join(filepartArr, "/", 0, filepartArr.length-1) + "/TestClass.class";

//		logger.debug("class to load : " + fullpath);
		
		InputStream is = new FileInputStream(fullpath);
		byte [] magicNumber = readNBytes(is,4);
//		0xfecababe
		String magicStr = HexUtils.toHexString(magicNumber);
		byte[] minorVersion = readNBytes(is,2);
		String minorVersionStr  = HexUtils.toHexString(minorVersion);
		
		byte[] majorVersion = readNBytes(is,2);
		String majorVersionStr  = HexUtils.toHexString(majorVersion);
		
		
		byte[] constantPoolCount = readNBytes(is,2);
		// big Endian
		int numberOfCP = constantPoolCount[0] << 8 | (constantPoolCount[1] & 0xFF); 
		int count = 0 ;
		
		
		CONSTANT_Class_info classInfo = new CONSTANT_Class_info();
		
		
		while(true){
			
			int tag = readNBytes(is,1)[0];
			
			switch(tag){
			case 7 : {
				classInfo.tag = (byte)tag;
				short index = readNBytesShort(is,2);
				System.out.println(index);
			}
			
			}
			
			
			System.out.println( "==tag:" + tag );
			break;
			
		}
		
		
		
		
	}
	
	
	// interesting thing , can not return null , but could declare a null array then return it; 
	static byte[] readNBytes(InputStream is , int n){
		Assert.isTrue(n > 0, "n must be longer than 0 ");
		byte [] result =  new byte[n];
		int b = -1;
		try {
			b = is.read(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		if(b != n) return null;
		return result;
	}

	
	static  short readNBytesShort(InputStream is , int n){
		Assert.isTrue(n > 0, "n must be longer than 0 ");
		byte [] result =  new byte[n];
		int b = -1;
		try {
			b = is.read(result);
			if(b != n) throw new RuntimeException("error occurs when read n ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		if(b != n) return null;
		short numberOfCP = (short)(result[0] << 8 | (result[1] & 0xFF)); 
		return numberOfCP;
	}
	
}
