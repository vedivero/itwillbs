package com.itwillbs.domain;

public class TestVO {
	
	private String name;
	private double price;
	
	// alt + shift + s + o  생성자 만들기
	public TestVO() {}
	public TestVO(String name,double price) {
		this.name = name;
		this.price = price;		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	

	//alt + shift + s + s 
	
	@Override
	public String toString() {
		return "TestVO [name=" + name + ", price=" + price + "]";
	}
	
}
