package com.mystore.testcases;

import org.apache.hc.core5.reactor.Command.Priority;
import org.testng.annotations.Test;

public class SampleTest {
	
	@Test
	public void test1() {
		System.out.println("Thread number : " + Thread.currentThread().getId());
	}
	
	@Test
	public void test2() {
		System.out.println("Thread number : " + Thread.currentThread().getId());
	}
	
	@Test
	public void test3() {
		System.out.println("Thread number : " + Thread.currentThread().getId());
	}
	

}
