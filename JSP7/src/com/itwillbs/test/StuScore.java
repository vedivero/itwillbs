package com.itwillbs.test;

public class StuScore {
	// 학생의 정보를 저장하는 객체  (국,영,수)
	private int kor;
	private int eng;
	private int math;
	
	// 기본 생성자는 컴파일러가 자동으로 생성
	// => 오버로딩된 생성자가 없을경우만 생성
	public StuScore(){}
	
	public StuScore(int k,int e,int m){
		kor = k;
		this.eng = e;
		math = m;		
	}
	
	// alt + shift + s  r
	//set/get
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	

	
	
	
	
	

}
