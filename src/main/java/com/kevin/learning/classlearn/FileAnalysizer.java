package com.kevin.learning.classlearn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.util.Assert;




class CONSTANT_Utf8_info{
	byte tag = 1;
	int length;
	byte [] data;
	
	@Override
	public String toString() {
		try {
			return new String(data,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}

class CONSTANT_Integer_info{
	byte tag =3;
	int len = 4;
	int val;
	byte [] data;
}

class CONSTANT_Float_info{
	byte tag = 4;
	int len = 4;
	float val;
	byte [] data;
}

class CONSTANT_Long_info{
	byte tag =5;
	int len = 8;
	long val;
	byte [] data;
}

class CONSTANT_Double_info{
	byte tag =6;
	int len = 8;
	double val;
	byte [] data;
}

class CONSTANT_Class_info{
	byte tag = 7;
	short index;
}

class CONSTANT_String_info{
	byte tag = 8;
	int len = 2;
	int  val;
	byte [] data;
}


class CONSTANT_Fieldref_info{
	byte tag = 9;
	short indexOfClassInfo ;
	short indexOfNameAndTypeInfo;
}



class CONSTANT_Methodref_info{
	byte tag = 10;
	short indexOfClassInfo ;
	short indexOfNameAndTypeInfo;
}


class CONSTANT_InrerfaceMethodref_info{
	byte tag = 11;
	short indexOfClassInfo ;
	short indexOfNameAndTypeInfo;
}

class CONSTANT_NameAndType_info{
	byte tag = 12;
	short indexOfClassInfo ;
	short indexOfNameAndTypeInfo;
}

public class FileAnalysizer {

//	static Logger logger = Logger.getLogger(FileAnalysizer.class);
	
	public static void main(String[] args) throws Exception {
		
				
//		byte[] bb = {1,2};
//		int s = bb[0]<< 8 | bb[1];
//		long l = 0xFFFFFFFFFFFFFFFFL;
		
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
		
		
		List<Object> poolList = new ArrayList<Object>();
		
		while (true) {
			
			if ( ++count == numberOfCP) break;
			
			int tag = readNBytes(is, 1)[0];
			switch (tag) {

			case 1: {
				CONSTANT_Utf8_info utf8Info = new CONSTANT_Utf8_info();
				short length = readNBytesShort(is, 2);
				byte[] data = readNBytes(is, length);
				utf8Info.length = length;
				utf8Info.data = data;
				utf8Info.length = length;

				poolList.add(utf8Info);
				break;
			}

			case 3: {
				CONSTANT_Integer_info intInfo = new CONSTANT_Integer_info();
				int intVal = readInt(is);
				intInfo.val = intVal;
				poolList.add(intInfo);
				break;
			}
			case 4: {
				CONSTANT_Float_info floatInfo = new CONSTANT_Float_info();
				float fVal = readFloat(is);
				floatInfo.val = fVal;
				poolList.add(floatInfo);
				break;
			}
			case 5: {
				CONSTANT_Long_info longInfo = new CONSTANT_Long_info();
				long lVal = readLong(is);
				longInfo.val = lVal;
				poolList.add(longInfo);
				break;
			}
			case 6: {
				CONSTANT_Double_info dInfo = new CONSTANT_Double_info();
				double dVal = readDouble(is);
				dInfo.val = dVal;
				poolList.add(dInfo);
				break;
			}
			case 7: {
				CONSTANT_Class_info classInfo = new CONSTANT_Class_info();
				classInfo.tag = (byte) tag;
				// 指向字符串字面量的索引
				short index = readNBytesShort(is, 2);
				classInfo.index = index;
				poolList.add(classInfo);
				break;
			}
			case 8: {
				CONSTANT_String_info strInfo = new CONSTANT_String_info();

				short s = readShort(is);
				strInfo.val = s;
				poolList.add(strInfo);
				break;

			}
			case 9: {
				CONSTANT_Fieldref_info fInfo = new CONSTANT_Fieldref_info();
				short index1 = readShort(is);
				short index2 = readShort(is);

				fInfo.indexOfClassInfo = index1;
				fInfo.indexOfNameAndTypeInfo = index2;
				poolList.add(fInfo);
				break;
			}
			case 10: {
				CONSTANT_Methodref_info fInfo = new CONSTANT_Methodref_info();
				short index1 = readShort(is);
				short index2 = readShort(is);

				fInfo.indexOfClassInfo = index1;
				fInfo.indexOfNameAndTypeInfo = index2;
				poolList.add(fInfo);
				break;

			}
			case 11: {
				CONSTANT_InrerfaceMethodref_info fInfo = new CONSTANT_InrerfaceMethodref_info();
				short index1 = readShort(is);
				short index2 = readShort(is);

				fInfo.indexOfClassInfo = index1;
				fInfo.indexOfNameAndTypeInfo = index2;
				poolList.add(fInfo);
				break;
			}
			case 12: {
				CONSTANT_NameAndType_info fInfo = new CONSTANT_NameAndType_info();
				short index1 = readShort(is);
				short index2 = readShort(is);

				fInfo.indexOfClassInfo = index1;
				fInfo.indexOfNameAndTypeInfo = index2;
				poolList.add(fInfo);
				break;
			}
			}

		}
		
		
		// 两个字节的 access_flag
		short accessFlag = readShort(is);
		
		//读取 thisclass:u2 superclass:u2 
		
		//读取 interface count:u2 ,interface[u2]
		
		
		//读取 Fields
		
		
		
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
	
	
	static  short readShort(InputStream is ){
		int needLen = 2;
		byte [] data = new byte[needLen];
		int read = 0;
		try {
			read = is.read( data );
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.isTrue( read == needLen , "尝试读取 " + needLen +" 字节，实际读取 " + read + "字节");
		
//		if(b != n) return null;
		short numberOfCP = (short)(data[0] << 8 | (data[1] & 0xFF)); 
		return numberOfCP;
	}
	
	static int readInt(InputStream is){
		int needLen = 4;
		byte [] data = new byte[needLen];
		int read = 0;
		try {
			read = is.read( data );
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.isTrue( read == needLen , "尝试读取 " + needLen +" 字节，实际读取 " + read + "字节");
		int result = data[0] << 24 & data[1] << 16 & data[2] << 8 & data[3];
		return result;
	}
	
	static float readFloat(InputStream is){
		int result = readInt(is);
		return Float.intBitsToFloat(result);
	}
	
	static long readLong(InputStream is){
		int needLen = 8;
		byte [] data = new byte[needLen];
		int read = 0;
		try {
			read = is.read( data );
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.isTrue( read == needLen , "尝试读取 " + needLen +" 字节，实际读取 " + read + "字节");
		long result = new Long(data[0]) << 56 & new Long(data[1]) << 48 & new Long(data[2]) << 40 &
				new Long(data[3]) & new Long(data[4]<<32) & new Long(data[5])<<24
				 & new Long(data[6]<<16) & new Long(data[7]<<8) & data[8];
		return result;
	}
	
	
	static double readDouble(InputStream is){
		long longRes = readLong(is);
		return Double.longBitsToDouble(longRes);
	}
	
	
}
