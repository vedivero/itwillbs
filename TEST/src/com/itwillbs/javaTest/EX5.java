package com.itwillbs.javaTest;

import java.util.ArrayList;
import java.util.Scanner;

class Location {
	private int x;
	private int y;

	public Location() {
	}

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 거리 계산동작
	public double distance(Location next) {
		// a^2 + b^2 = c^2
		// (다음x - 기준x)^2 + (다음y - 기준y)^2 = 길이^2
		// C^2
		double c = (next.x - x) * (next.x - x) + (next.y - y) * (next.y - y);

		return Math.sqrt(c);
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}

}

public class EX5 {

	public static void main(String[] args) {

		ArrayList<Location> totalDis = new ArrayList<Location>();

		Scanner s = new Scanner(System.in);
		// 키보드 입력 좌표(x,y)-> 좌표 객체 생성->ArrayList 저장

		totalDis.add(new Location(0, 0));
		for (int i = 0; i < 5; i++) {
			System.out.println("강아지 이동경로 입력하시오.");
			// int x = s.nextInt();
			// int y = s.nextInt();
			// Location p = new Location(x, y);
			// totalDis.add(p);
			totalDis.add(new Location(s.nextInt(),s.nextInt()));

		}
		totalDis.add(new Location(0, 0));
		System.out.println(totalDis);
		
		// 좌표 저장 완료!
		
		double total = 0.0;
		
		for(int i=0;i<totalDis.size()-1;i++){
			// 0 번좌표 - 1번 좌표
			// 1 - 2
			// 2 - 3
			total +=
			totalDis.get(i).distance(totalDis.get(i+1));
		}
		
		System.out.println(" 총 이동 거리 : "+total);
		

	}

}
