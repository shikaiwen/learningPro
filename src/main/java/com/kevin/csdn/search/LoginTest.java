package com.kevin.csdn.search;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {

	
	public static void main(String[] args) throws Exception, IOException {
		
			String url = "https://passport.csdn.net/account/login;jsessionid=2E048129E5AA0440632E0D83F50577D7.tomcat2?from=http://my.csdn.net/my/mycsdn";
		
		  HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(url).openConnection();  
		  String data = "username=googlevsbing%40126.com&password=shikaiwen123456&lt=LT-11335-xSecvteSwIZJelNFWUysvRhfEHveTT&execution=e1s1&_eventId=submit";
//		        httpURLConnection.connect();  
		      httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");  
		         httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
		         httpURLConnection.setDoInput(true);    //true��ʾ������������,��ȡ��������Ӧ������,������Ĭ��ֵΪtrue    
		         httpURLConnection.setDoOutput(true);   //true��ʾ�����������,��Զ�̷�������������,������Ĭ��ֵΪfalse    
		         httpURLConnection.setUseCaches(false); //��ֹ����    
		         httpURLConnection.setReadTimeout(30000);    //30���ȡ��ʱ    
		         httpURLConnection.setConnectTimeout(30000); //30�����ӳ�ʱ    
		         httpURLConnection.setRequestMethod("POST");    
		           
		         httpURLConnection.setRequestProperty("Content-Length", data.length() + "");  
		           
		         OutputStream writer = httpURLConnection.getOutputStream();  
		         writer.write(data.getBytes());  
//		         writer.write( new byte[]{13,10,13,10} );  
//		         writer.flush();  
		           
		         InputStream in = httpURLConnection.getInputStream();    
		         ByteArrayOutputStream buffer = new ByteArrayOutputStream();    
		         byte[] buff = new byte[1024];    
		         int len = -1;    
		         while((len= in.read(buff)) != -1){    
		             buffer.write(buff, 0, len);    
		         }    
		         String result  = buffer.toString("utf-8");    
		           
		         
		         
		         System.out.println(result);  
		           
		
		
	}
	
}
