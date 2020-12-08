package com.itwill.aop;

public class Calculator {
	// 계산기 객체 (target)
	
	public void add(int x,int y){
		int result = x + y;
		System.out.println(" 결과 (add): "+result);
	}
	
	public void sub(int x,int y){
		int result = x - y;
		System.out.println(" 결과 (sub): "+result);
	}
	
	public void mul(int x, int y){
		int result = x * y;
		System.out.println(" 결과 (mul): "+result);
	}
	
	public void div(int x,int y){
		int result = x / y;
		System.out.println(" 결과 (div): "+result);
	}

}
