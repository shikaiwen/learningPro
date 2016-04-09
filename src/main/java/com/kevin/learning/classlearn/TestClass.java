package com.kevin.learning.classlearn;

public class TestClass implements Super{
	 
private static final int staticVar = 0;
 
private int instanceVar=0;
 
public int instanceMethod(int param){
 return param+1;
 }
 
}
 
interface Super{ }