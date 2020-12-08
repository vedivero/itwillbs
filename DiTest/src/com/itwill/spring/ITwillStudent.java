package com.itwill.spring;

public class ITwillStudent implements Student{
	
	private String name;
	private int classNum;
	
	public ITwillStudent(){}
	public ITwillStudent(String name){
		this.name = name;
	}
	public ITwillStudent(String name,int classNum){
		this.name = name;
		this.classNum = classNum;
	}
	
	
    // set() 사용해서 정보 주입
	public void setName(String name) {
		this.name = name;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}

	@Override
	public void hello() {
		System.out.println("안녕하세요~! "+classNum+"강의장, "+name+"입니다.");
	}
	
}
