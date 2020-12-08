package com.itwillbs.javaTest;

import java.util.Scanner;

public class EX1 {
	public static void main(String args[]){
		System.out.println("연산을 입력하시오>>");
		
		// 키입력 -> 공백으로 구분  10 + 20
		// 실수형 데이터 입력
		Scanner scan = new Scanner(System.in);
		double dnum1 = scan.nextDouble();
		String op = scan.next();
		double dnum2 = scan.nextDouble();
		
		double result = 0;
		// 연산
		switch (op) {
		case "+":
			result = dnum1 + dnum2;
			break;
		case "-":
			result = dnum1 - dnum2;
			break;
		case "*":
			result = dnum1 * dnum2;
			break;
		case "/":
			if(dnum2 == 0){
				System.out.println("0으로 나눌 수 없음!");
				return;
			}
			result = dnum1 / dnum2;
			break;

		default:
			System.out.println("잘못된 연산기호 입력!");			
		}
		
		
		// 결과 출력
		System.out.println("결과 : "+ result);
		
	}
	

}
