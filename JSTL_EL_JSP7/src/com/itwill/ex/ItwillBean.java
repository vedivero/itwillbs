package com.itwill.ex;

public class ItwillBean {
	// 자바빈 객체 
	
	private String id;
	private String pw;
	private String name;
	private int age;
	private String tel;
	
	// public ItwillBean(){}
	
	// alt shift s + r
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	// alt shift s + s
	@Override
	public String toString() {
		return "ItwillBean [id=" + id + ", pw=" + pw + ", name=" + name + ", age=" + age + ", tel=" + tel + "]";
	}
	
	
	

}
