package com.kevin.testtools;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

/**
 * @WebAppConfiguration http://stackoverflow.com/questions/19135492/webapplicationcontext-doesnt-autowire
 * @author Administrator
 *
 */


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations="classpath:applicationContext.xml")
//也可以继承AbstractJUnit4SpringContextTests 
public class JunitTest1 {

	@Resource
	private WebApplicationContext wac;
	
	
	private MockMvc mockMvc;
	@Before
	public void setup(){
		mockMvc = webAppContextSetup(wac).build();
	}
	
	@Test
	public void test2(){

		
		MockHttpServletRequestBuilder b = post("/agentDetail/getRecommendations").param("userName", "admin").param("password", "1");
        try {
			
        	System.out.println("df");
        	ResultActions re = mockMvc.perform(b);
        	re.andExpect(status().isOk()).andDo( print()  )  ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	 junit 手动测试,通过注入controller，直接访问方法
	public void test1(){
		MockHttpServletRequest req = new MockHttpServletRequest();
	}

	
	
}
