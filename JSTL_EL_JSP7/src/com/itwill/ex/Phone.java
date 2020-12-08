package com.itwill.ex;

public class Phone {
	// 휴대폰 - (기종,전화번호)
	private String model;
	private String tel;
	
	public Phone() {}
	public Phone(String model,String tel) {
		this.model = model;
		this.tel = tel;	
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		return "Phone [model=" + model + ", tel=" + tel + "]";
	}
	
	

}
