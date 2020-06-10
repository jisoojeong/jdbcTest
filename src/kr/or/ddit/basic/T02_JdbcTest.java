package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Select  예제
 */
public class T02_JdbcTest {
/*
 	문제1) 사용자로부터  lprod_id값을 입력받아 입력한 값보다  lprod_id가 큰 자료들을 출력하시오.
 	
 		
 	문제 2) lprod_id값을 2개 입력받아서 두 값 중 작은 값부터 큰 값 사이의 자료를 출력하시오.
 */
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; //쿼리문이 select인 경우에 필요.
		
		Scanner s = new Scanner(System.in);
		System.out.println("lpord_id값을 입력해주세요 : ");
		int input1 = Integer.parseInt(s.nextLine());
		//int input2 = Integer.parseInt(s.nextLine());
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
	
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "jisoo";
			String password = "java";
			
			conn = DriverManager.getConnection(url, userId, password);
			
		
			stmt = conn.createStatement();
			
		
			String sql ="select * from lprod where lprod_id >"+ input1; // 실행할 SQL문
			
		
			rs = stmt.executeQuery(sql); 
		
			System.out.println("실행한 쿼리문  :" + sql);
			System.out.println("=== 쿼리문 실행결과 ===");
			
		
			
			while(rs.next()) {
		
				System.out.println("lprod_id : " +  rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("--------------------------------------");
				
			}
			
			System.out.println("출력 끝......");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			// 6. 종료(사용했던 지원을 모든 반납한다.)
			if(rs != null) try{rs.close();}catch(SQLException e) {}
			if(stmt != null) try{stmt.close();}catch(SQLException e) {}
			if(conn != null) try{conn.close();}catch(SQLException e) {}
		}
	}
}
