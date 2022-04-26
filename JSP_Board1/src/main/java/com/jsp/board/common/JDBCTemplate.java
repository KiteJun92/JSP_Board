package com.jsp.board.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemplate {
	
	// JDBC 관련 공통 내용을 모아둔 클래스
	// getConnection() : 커넥션 반환
	// close(stmt | pstmt | rs | conn) : 자원 반환 메서드
	// commit() / rollback() : 트랜잭션 처리용 메서드
	
	private static Connection conn = null;
	
	// DB에서 커넥션 받아오기
	public static Connection getConnection() {
		try {
			// Connection Pool - 미리 커넥션 만들어두고 요청 시 빌려주었다가 반환받는 방식
			// JNDI
			// Servers에 존재하는 context.xml 파일을 찾는 작업
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");  // java:comp/env   응용 프로그램 환경 항목
			// context.xml 파일에서 name이 "jdbc/oracle"인 DataSource를 얻어옴
			// DataSource : DriverManager를 대체하는 객체로 
			// Connection 생성, Connectoin pool을 구현하는 객체
			DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// Connection 반환
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Statement 반환 + 다형성을 이용한 PreparedStatement 반환
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ResultSet 반환 
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// rollback용 메서드
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				// conn이 참조하는 Connection 객체가 있고
				// 그 객체가 반환되지 않았을 때
				conn.rollback();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// commit용 메서드
		public static void commit(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					// conn이 참조하는 Connection 객체가 있고
					// 그 객체가 반환되지 않았을 때
					conn.commit();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
}
