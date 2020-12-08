package com.itwill.spring;

public class SubPerson implements IPerson{
	// 사람의 정보를 저장 + 사람의 기능(sayHello)
	private String name;
	private int age;
	
//	public String getName() {
//		return name;
//	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public void sayHello() {
		System.out.println("SayHello : "+name+","+age);		
	}
	//alt shift s + v
	@Override
	public String toString() {
		return "사용자 이름:"+name+",나이 :"+age;
	}

}
