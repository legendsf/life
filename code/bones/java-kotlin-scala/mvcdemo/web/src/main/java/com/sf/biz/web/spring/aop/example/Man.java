package com.sf.biz.web.spring.aop.example;

import org.springframework.stereotype.Component;

@A2
@Component
public class Man  extends Human {

	@Override
	public void run() {
		System.out.println("Man runs.");
	}

}
	
 
	
