package com.itwillbs.test;

class Parent {
	int a;

	void pprn() {
		System.out.println("Parent_pprn()!");
	}
}

class Child extends Parent {
	public void cprn() {
		System.out.println("Child_cprn()!");
	}
	// public > protected > 기본접근지정자 > private
	// 메서드 오버라이딩시 접근지정자는 부모의 메서드보다 범위가 줄어들어서는 안됨.
	// 부모의 메서드보다 범위가 증가는 가능

	/// *기본접근지정자*/void pprn() {
	public void pprn() {
		super.pprn();
		System.out.println("Child_pprn()!");
	}
}

public class Test1 {

	public static void main(String[] args) {

		Parent p = new Parent();
		p.pprn();
		// p.cprn();
		System.out.println("--------------------");
		Child c = new Child();
		c.cprn();
		c.pprn();

		System.out.println("-----------------------------");

		// * 상속관계 일때만 가능
		// 업 캐스팅 : (자동형변환) 서브클래스를 부모클래스로 형변환
		// 슈퍼클래스의 레퍼런스에 서브클래스의 인스턴스를 저장.
		// 다운 캐스팅 : (강제형변환) 부모클래스를 서브클래스로 형변환
		// 서브클래스의 레퍼런스에 슈퍼클래스의 인스턴스를 저장.

		// Child c = new Child();
		Parent p1 = new Child();
		// Parent p2 = c;
		p1.pprn(); // (o) 상속받은 부분
		// p1.cprn();(x)
		// * 업캐스팅은 상속받은 부분만 사용가능
		// * 참조영역이 감소했다.
		// * 컴파일러가 자동으로 형변환

		// 다운캐스팅

		// Parent p = new Parent();
		// Child c1 = new Parent();
		// -> 컴파일 에러 : 실행전
		// Child c1 = (Child) new Parent();
		// -> 예외 (Exception) :실행후
		// c1.pprn();
		// c1.cprn();
		// Child c2 = p;

		// * 다운캐스팅은 컴파일러가 자동으로 형변환 X
		// -> 해당 객체가 존재하지 않을수 있기때문
		// => 개발자가 직접 타입을 구현 => 예외 발생

		// Parent pp = new Child();// 업캐스팅
		// Child c1 = (Child)pp;
		// // -> 강제형변환 : 문제를 인지하고 책임을 지겠다.(개발자)
		// c1.pprn();
		// c1.cprn();

	}

}
