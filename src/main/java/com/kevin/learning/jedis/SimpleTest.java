package com.kevin.learning.jedis;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class SimpleTest {

	Jedis jedis = null;
	@Before
	public void before(){
		jedis = new Jedis("172.16.6.196",6379);
	}
	
	//字符串操作
	@Test
	public void testString(){
		
		jedis.set("foo", "bar2");
		String value = jedis.get("foo");
		System.out.println(value);
		jedis.close();
	}
	
	@Test
	public void testList(){
	}
}
