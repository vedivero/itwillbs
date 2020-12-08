package com.itwillbs.test;

public class Calc {
	
	// 계산기 클래스
	
	// 총합 계산후 출력
	public void Sum(int k,int m,int e){
		System.out.println("계산기 총합 : "+(k+m+e));
	}	
	// 평균을 계산후 출력
	public void Avg(int k,int m,int e){
		System.out.println("계산기 평균 : "+(k+m+e)/3.0);
	}
	
	// 총합 - 학생 점수 객체 사용
	public void Sum(StuScore s){
		System.out.println("총합 : "+(s.getKor()+s.getEng()+s.getMath()));		
	}
	
	// 평균 - 학생 점수 객체 사용
	public void Avg(StuScore s){
		
		System.out.println("평균 : "+(s.getEng()+s.getKor()+s.getMath())/3.0);
		
	}
	

}
