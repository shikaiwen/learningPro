package com.kevin.learning.basic.jdk.generic;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

/**
 * https://segmentfault.com/a/1190000003831229
 * https://segmentfault.com/a/1190000002646193
 * @author root
 *
 */

public class ParamProblem {

	public static void main(String[] args)  throws Exception {
		TypeVariable<Class<ParamProblem>>[]  arr = ParamProblem.class.getTypeParameters();
		t1();
	}
	
	public static void t1() throws Exception{
		
		Map map = new HashMap();
		map.put("time", 30);
		map.put("code", "3354");
		
		String resultStr = invoke(map);
		System.out.println(resultStr);
		
		
	}
	
	public static  String invoke(Map<String,String> params) throws Exception, SecurityException{
		
		StringBuffer sb = new StringBuffer();
		
		for(Map.Entry<String, String> k : params.entrySet()){
			
			sb.append( k.getKey() );
			sb.append("=");
//			String v = 
				Object obj =	k.getValue();
				Method m = obj.getClass().getDeclaredMethod("toString", null);
				String r = (String)m.invoke(obj, null);
//				obj.toString();
			sb.append( r );
			sb.append(";");
		}
		
		return sb.toString();
	}
	
}
