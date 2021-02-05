package com.sf.biz.web.spring.aop.example;

import org.springframework.stereotype.Component;

@Component
public class Boy extends Man {

	@Override
	public void jump() {
		System.out.println("Boy jump.");
	}
}
 
