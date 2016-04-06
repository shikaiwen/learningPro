package com.kevin.learning.java.scripting;
import javax.script.*;

public class FirstScripting {

	public static void main(String[] args)throws ScriptException {
		test1();
	}
	
	public static void test1() throws ScriptException{
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		engine.eval("print('Hello world !')");
	}
}
