package com.kevin.learning.json;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import ognl.Ognl;

public class FastJsonAndOgnl {

	@Test
	public void t1() throws Exception{
		byte [] data = FileUtils.readFileToByteArray(new File("D:/log/complex.txt"));
		String str = new String(data);
		
		Map map = JSON.parseObject(str, Map.class);
		
		Object obj = Ognl.getValue("data.data.dwList", map);
	}
}
