package com.itwillbs.javaTest;

class MyPoint{
	int x;
	int y;

	public MyPoint(){}
	public MyPoint(int x,int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		//return "MyPoint("+x+","+y+")";
		return getClass().getName()+"("+x+","+y+")";
	}
	
}


public class EX4 {

	public static void main(String[] args) {
		// "MyPoint(3,20)"
		MyPoint a = new MyPoint(3,20);
		System.out.println( a );
	}

}
