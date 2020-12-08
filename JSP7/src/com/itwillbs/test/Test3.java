package com.itwillbs.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

// public class의 의미
// -> 하나의 파일에는 하나의 public 클래스만 있어야한다.
// => 메인을 포함하고있는 클래스다 라는 의미를 포함하고있음. 

// -> inner클래스에서는 public 클래스 추가 생성이 가능하다. 

class Test{
	public class TestA{
		// inner클래스
	} 	
}

public class Test3 {

	public static void main(String[] args){
		
		// 컬렉션 클래스 - 제네릭 (JDK 5~ )
		// -> 자료구조 학문을 구현한 클래스
		// => 요소라고하는 데이터를 가변인자의 객체에 저장
		// => 고정길이 배열의 단점을 보완, 데이터의 삽입,삭제,검색 기능이 뛰어남
		
		// Set,List,Map(Table)
		
		// Set계열 클래스 : 서로 다른타입의 데이터를 저장 가능(object 업캐스팅),
		// 저장공간의 크기가 자동으로 증가, 순서를 포함하지 않음, 중복허용X
		
		// 업캐스팅
		//Set set = new HashSet();
		// Set 인터페이스를 구현한 서브클래스
		HashSet set = new HashSet();
		
		System.out.println("요소의 개수 : "+set.size());
		
		set.add("하나");
		set.add(2);
		set.add(3.124);
		set.add("넷");
		set.add("five");
		set.add(6);
		set.add(6);
		
		System.out.println("요소의 개수 : "+set.size());
		System.out.println(set);
		
		// for(int i=0;i<set.size();i++){
		// System.out.println(set.get(i));
		// }
		
		Iterator is = set.iterator();
		
		while(is.hasNext()){
			System.out.println(is.next());			
		}
		
		
		
		// 컬렉션 클래스들은 toString() 메서드가 구현되어있음.
		
		System.out.println("-----------------------------");
		// List 계열 : 서로 다른 타입을 저장가능(업캐스팅),
		// 자동으로 크기가 증가, 순서가 저장됨, 중복허용
		// => ArrayList, Vector, Stack, LinkedList
		
		ArrayList list = new ArrayList();
		// 저장할때 요소의 위치(인덱스)값을 사용
		// => 배열처럼 항상 0번 부터 시작
		
		System.out.println(" 요소의 개수 : "+list.size());
		
		list.add("하나");
		list.add(2);
		list.add(3.124);
		list.add("넷");
		list.add("five");
		list.add(6);
		list.add(6);
		
		System.out.println(" 요소의 개수 : "+list.size());
		System.out.println(list);
		
		System.out.println(list.get(3));
		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));			
		}
		// 확장for (for-each) 
		// for(해당 타입의 요소 :반복할 배열/컬렉션){}
		
		for(Object obj :list){
			System.out.println(obj);			
		}
		
		System.out.println("------------------------------");
		// 인덱스가 포함되어 있음
		
		//int value = list.indexOf("하나");
		int value = list.indexOf("둘");
		System.out.println(value);
		
		list.add(3, "안녕하세요");
		System.out.println(list);
		
		list.set(3, "Hello");
		System.out.println(list);
		
		// list에 "five" 데이터가 있을경우  해당 데이터를 "다섯" 변경
		
		System.out.println("-------------------------------");
		//System.out.println(list.indexOf("five"));
		int val = list.indexOf("five");
		if(val != -1){ // 데이터가 있을경우
			list.set(val, "다섯");
		}
		System.out.println(list);
		
		System.out.println("----------------------------------------");
		
		// Iterator인터페이스 - 반복자
		// 해당 컬렉션에서 현재위치, 다음단계로의 이동동작을 반복가능
		
		// 컬렉션 클래스들은 Collections 인터페이스의 하위 클래스/인터페이스 이다.
		// => 모든 컬렉션 클래스 들은  부모의 메서드 iterator() 모두 사용가능하다.
		// => "디자인 패턴"
		
		// list 객체를 iterator 객체로 변환(반복할수 있도록준비/변환)
		Iterator iter = list.iterator();
		
		while(iter.hasNext()){ // 다음 데이터가 있으면 반복/ 없으면 끝
			System.out.println(iter.next()); // 있으면 출력
		}


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
