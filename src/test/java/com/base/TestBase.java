package com.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import zjy.RunApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RunApplication.class})
public class TestBase {

	@Test
	public void testOne(){
		System.out.println(123);
	}

}
