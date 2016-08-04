package com.jjshome.tool.kevin;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class JavaClientPlugin extends PluginAdapter{

	Logger logger = Logger.getLogger(JavaClientPlugin.class);
	
	//改变方法名称中的 Example,改变方法名称中的Example，改变方法参数的example
	void changeExampleNameInMethod(Method method){
		String oldName = method.getName();
		Pattern p = Pattern.compile("Example");
		Matcher m = p.matcher(oldName);
		String newName = m.replaceAll("Criteria");
		method.setName(newName);
		
		List<Parameter> parameter = method.getParameters();
		
		
		try {
			
			for(int i = 0 ; i < parameter.size();i++){
				
				Parameter p1 = parameter.get(0);
				if(! p1.getName().equals("example")) continue;
				
				
				Field nameField = p1.getClass().getDeclaredField("name");
				
				logger.debug("参数名称，"+ p1.getName());
				nameField.setAccessible(true);
				nameField.set(p1,"criteria");
				logger.debug("更改后参数名称，"+ p1.getName());
			}
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@Override
	public boolean clientCountByExampleMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		this.changeExampleNameInMethod(method);
		return super.clientCountByExampleMethodGenerated(method, topLevelClass, introspectedTable);
	}

	@Override
	public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		
		this.changeExampleNameInMethod(method);;
		return super.clientCountByExampleMethodGenerated(method, interfaze, introspectedTable);
	}
	
	
	
	@Override
	public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		this.changeExampleNameInMethod(method);;
		return super.clientDeleteByExampleMethodGenerated(method, interfaze, introspectedTable);
	}


	@Override
	public boolean clientDeleteByExampleMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		this.changeExampleNameInMethod(method);;
		return super.clientDeleteByExampleMethodGenerated(method, topLevelClass, introspectedTable);
	}



	@Override
	public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		this.changeExampleNameInMethod(method);;
		return super.clientUpdateByExampleSelectiveMethodGenerated(method, interfaze, introspectedTable);
	}


	@Override
	public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		this.changeExampleNameInMethod(method);;
		return super.clientUpdateByExampleSelectiveMethodGenerated(method, topLevelClass, introspectedTable);
	}





	@Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
		this.changeExampleNameInMethod(method);
        return true;
    }
	
	
	
	
	
	public boolean validate(List<String> warnings) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	

	
}
