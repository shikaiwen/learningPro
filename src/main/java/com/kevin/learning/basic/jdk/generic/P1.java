package com.kevin.learning.basic.jdk.generic;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import org.junit.Test;

public class P1 {

	public static void main(String[] args) {
		
		 
	}
	
	@Test
	public void t2(){
		P2<String> p2 = new P2<String>();
		Class c = p2.getClass().getComponentType();
		
		TypeVariable<?>[]  t = p2.getClass().getTypeParameters();
		String name = t[0].getName();
		Type [] types = t[0].getBounds();
	}
	
	
	public void t1(){
		TypeVariable<Class<P2>>[] d = P2.class.getTypeParameters();
		String name = d[0].getName();// T
		Type[] types = d[0].getBounds();
	}
}

class P2<T>{
	
	
}