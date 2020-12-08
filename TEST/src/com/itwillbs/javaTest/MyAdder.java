package com.itwillbs.javaTest;

interface AdderInterface {
	int add(int x, int y); // x + y 값을 리턴

	int add(int n); // 1~n 까지의 합을 리턴
}

public class MyAdder implements AdderInterface {
	@Override
	public int add(int x, int y) {
		return x+y;
	}

	@Override
	public int add(int n) {
		int sum =0;
		for(int i=1;i<=n;i++){
			sum +=i;
		}
		return sum;
	}

	public static void main(String[] args) {
		MyAdder adder = new MyAdder();
		System.out.println(adder.add(5, 10)); // 15 출력
		System.out.println(adder.add(10)); // 55 출력
										

	}

}
