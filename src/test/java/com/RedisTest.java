package com;

import com.base.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zjy.tools.RedisUtil;

public class RedisTest extends TestBase {

	@Autowired
	RedisUtil redisUtil;

	@Test
	public void redisTest(){
		redisUtil.set("key",1);
		System.out.println(redisUtil.get("key"));
	}

}
