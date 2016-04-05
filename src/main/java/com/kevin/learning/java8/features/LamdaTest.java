package com.kevin.learning.java8.features;

import com.sun.org.apache.bcel.internal.classfile.Method;

public class LamdaTest {

	 interface hello {  
	        String sayHello(String name);  
	    }  
	
	 
	public static void main(String[] args) {
		
		Method result = ( Method m ) ->{
			Method re = null;
			for( Method m : LamdaTest.class.getMethods() ){
				if(m.getName().equals("t1")){
					re = m;
				}
			}
			return re;
		};
		
	     hello he = (String name) -> {  
	            int y = 1;  
	            String hello = "hello, " + name;  
	            return hello;  
	            };  
	        System.out.println(he.sayHello("kevin "));  
	}
	
	public void t1(){}

	
	
}
