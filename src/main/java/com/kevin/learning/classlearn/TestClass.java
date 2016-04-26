package com.kevin.learning.classlearn;

public class TestClass implements Super{
	 
private static final int staticVar = 14;
 
private int instanceVar=0;
 
public static void main(String[] args) {
	System.out.println("hehehehe");
}

public int instanceMethod(int param){
 return param+1;
 }
 
}
 
interface Super{ }