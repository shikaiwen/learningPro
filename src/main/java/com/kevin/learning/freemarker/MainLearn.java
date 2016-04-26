package com.kevin.learning.freemarker;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MainLearn {

	
	@Test
	public void test1() throws Exception{
		Configuration config = new Configuration();
		Reader reader = new StringReader(" <#if username??> ${username} <#else> anonymous </#if>"); 
		Template temp1 = new Template("temp1",reader);
//		Template.getPlainTextTemplate(name, content, config)
		
		PrintWriter out = new PrintWriter(System.out);
		temp1.process(Collections.singletonMap("username", "kevin"),  out );
		
//		config.getTemplate(name)
	}
}
