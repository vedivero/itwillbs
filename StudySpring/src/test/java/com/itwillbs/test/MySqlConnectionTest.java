package com.itwillbs.test;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Mysql서버에 접속이 가능한지를 체크
public class MySqlConnectionTest {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/springdb";
	private static final String ID = "root";
	private static final String PW = "1234";

	// mysql 접속 테스트동작 -> 메서드
	// 테스트할 메서드 위에 @Test 애노테이션 사용
	// 'Junit을 사용한 테스트용 코드입니다' 표시

	// @Before : 테스트 이전에 미리 처리(준비) 되어야하는 동작/메서드
	// 위쪽에 사용됨.

	// @After : 테스트 실행 후 자동으로 실행된는 메서드에 사용

	@Before
	public void beforeTest() throws Exception {
		System.out.println("테스트 이전 실행!!!");
	}

	@Test
	public void testCon() throws Exception {
		/*
		 * // 드라이버로드 Class.forName(DRIVER); // 디비연결 Connection con =
		 * DriverManager.getConnection(URL,ID,PW);
		 * 
		 * System.out.println("연결 정보 : "+con);
		 */
		// try-with 구문(JDK 1.7~)
		// => finally 구문을 제거한 try-catch구문

		// AutoCloseable인터페이스를 구현한 객체
		// => DB관련 동작
		//		try(자동자원해제 객체를 구현하는 객체) {
		//			
		//		}catch (Exception e) {
		//		
		//		}
		
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL,ID,PW)){
			
			System.out.println("객체 정보 확인 : "+con);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

	@After
	public void afterTest() throws Exception {
		System.out.println("테스트 후 실행 !");
	}

}
