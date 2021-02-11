package com.kh.common.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	
	//singleton 패턴
	//클래스의 인스턴스가 하나만 생성되어야 할 때 사용하는 디자인 패턴
	//클래스 이름으로 static변수
	private static JDBCTemplate instance;
	
	
	//기본생성자를 private으로 만들어서 외부에서 JDBCTemplate의 생성을 차단
	private JDBCTemplate() {
		// OracleDriver를 JVM에 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//클래스를 상속 받아야하나 ?
	public static JDBCTemplate getInstance() {
		if (instance == null) { //인스턴스가 null = 한번도 템플릿이 만들어진적이 없다
			instance = new JDBCTemplate(); //새로운 템플릿을 만들어서 인스턴스 레퍼런스에 넣어줌
		}
		return instance; //한번이라도 생성되면 이미 생성되어있는 인스턴스 반환
	}
	//더 많고 유용한 싱글톤 패턴은 서치

	// Connection 객체를 만들 메서드
	public Connection getConnection() {

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "BM";
		String password = "USER11";

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			// Transaction을 개발자가 관리하기 위해 AutoCommit을 false로 지정
			conn.setAutoCommit(false);// 수동 커밋으로 변경

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

	// commit 수행
	public void commit(Connection conn) {
		try {
			conn.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close(ResultSet rset) {
		try {
			// 예외처리 필요
			// rset이 null이 아니고 rset이 닫히지 않을때만 실행
			// 아니면 닫힌거니까 아무것도 안해줘도 됨
			if (rset != null && rset.isClosed()) {

				rset.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close(Statement stmt) {

		try {
			if (stmt != null && stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close(Connection conn) {
		try {
			if (conn != null && conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close(ResultSet rset, Statement stmt, Connection conn) {
		try {

			if (rset != null && rset.isClosed()) {

				rset.close();
			}
			if (stmt != null && stmt.isClosed()) {
				stmt.close();
			}

			if (conn != null && conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close(ResultSet rset, Statement stmt) {
		try {

			if (rset != null && rset.isClosed()) {

				rset.close();
			}
			if (stmt != null && stmt.isClosed()) {
				stmt.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close(Statement stmt, Connection conn) {
		try {

			if (stmt != null && stmt.isClosed()) {
				stmt.close();
			}

			if (conn != null && conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
