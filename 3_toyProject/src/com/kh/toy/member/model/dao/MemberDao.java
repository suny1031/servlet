package com.kh.toy.member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.DataAccessException;
import com.kh.toy.common.jdbc.JDBCTemplate;
import com.kh.toy.member.model.vo.Member;



public class MemberDao {

	// DAO : DBMS에 접근해 데이터를 조회, 수정, 삽입, 삭제 요청을 보내는 클래스
	// DAO의 메서드는 한번에 하나의 쿼리만 처리하도록 작성한다
	public MemberDao() {
	}

	JDBCTemplate jdt = JDBCTemplate.getInstance(); // 템플릿 생성

	// 로그인
	// 매개변수로 ID와 PASSWORD를 전달받아, ID와 PASSWORD로 식별할 수 있는
	// 회원정보를 반환하는 메서드

	public Member memberAuthenticate(Connection conn, String userId, String password) {

		// 변수선언 import java.sqllo
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {

			// *서비스에서 conn으로 만듬

			String query = "select * from tb_member where user_id = ?  and password =  ?  and is_leave = 0 ";

			pstm = conn.prepareStatement(query);
			// 4. prepareStatement의 쿼리를 완성

			pstm.setString(1, userId);
			// setString(1,userId) 1: 첫번째 ?에 들어갈 값 / userId: 두번째 ?에 들어갈 값
			pstm.setString(2, password);

			// 5. 쿼리문을 실행하고 질의결과(ResultSet)을 받음
			rset = pstm.executeQuery();

			// 6. resultSet 인스턴스에 담겨있는 정보를 VO객체로 옮겨담기
			// resultSet.next();
			// next() : 현재 row에서 다음 row의 데이터를 읽어온다 [true를 반환]
			// 만약 다음 row가 존재하지 않으면 [false를 반환]
			if (rset.next()) {
				member = new Member();
				member.setUserId(rset.getString("user_id"));
				member.setPassword(rset.getString("password"));
				member.setGrade(rset.getString("grade"));
				member.setTell(rset.getString("tell"));
				member.setRegDate(rset.getDate("reg_date"));
				member.setEmail(rset.getString("email"));
				member.setRentableDate(rset.getDate("rentable_date"));
				member.setIsLeave(rset.getInt("is_leave"));
			}

			// System.out.println(member);
		} catch (SQLException e) {
			// 우리가 만든 예외
			throw new DataAccessException(ErrorCode.SM01 , e);
		} finally {
			jdt.close(rset, pstm);
			// 한줄로 줄이기 가능
		}

		return member;

	}

	public Member selectMemberByld(Connection conn, String userId) {

		Member member = null; // return할때 사용
		PreparedStatement pstm = null; // 쿼리를 실행하기 위한 객체
		ResultSet rset = null; // 쿼리의 실행 결과를 담을 변수

		try {

			String query = "select * from tb_member where user_id = ? ";

			// 3. 쿼리문 실행용 객체를 생성
			pstm = conn.prepareStatement(query);

			pstm.setString(1, userId);
			// 4. 쿼리문 작성

			// 5. 쿼리문 실행하고 결과(resultSet)를 받음
			rset = pstm.executeQuery(); // rset로 쿼리 결과에 접근가능함

			if (rset.next()) { // 데이터가 담겨왔으면 member에 담아 줄 수 있다
				member = new Member(); // 이 시점에서 초기화해줘야 한번이라도 돌았을때 넣어줄 수 있다
				member.setUserId(rset.getString("user_id")); // user_id컬럼을 String으로 받아옴
				member.setPassword(rset.getString("password"));
				member.setGrade(rset.getString("grade"));
				member.setTell(rset.getString("tell"));
				member.setRegDate(rset.getDate("reg_date"));
				member.setEmail(rset.getString("email"));
				member.setRentableDate(rset.getDate("rentable_date"));
				member.setIsLeave(rset.getInt("is_leave"));
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
			//여기서 이넘 저장하고 로그찍고 함
		} finally {
			jdt.close(rset, pstm);
		}

		return member;

	}

	public ArrayList<Member> selectMemberList(Connection conn) {

		ArrayList<Member> memberArrayList = new ArrayList<>();

		PreparedStatement pstm = null;
		// Statement stmt = null;
		ResultSet rset = null;

		try {
			String query = "select * from tb_member";

			pstm = conn.prepareStatement(query);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Member member = new Member();
				member.setUserId(rset.getNString("user_id"));
				member.setPassword(rset.getNString("password"));
				member.setEmail(rset.getNString("email"));
				member.setGrade(rset.getNString("grade"));
				member.setRegDate(rset.getDate("reg_date"));
				member.setTell(rset.getNString("tell"));
				member.setIsLeave(rset.getInt("is_leave"));
				memberArrayList.add(member);

			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return memberArrayList;

	}

	public int insertMember(Connection conn, Member member) {
		// jdbc코딩을 진행

		int insert = 0;

		PreparedStatement pstm = null;
		// Statement stmt = null;
		// ResultSet 필요없음

		try {

			/*
			 * String query = "insert into tb_member(user_id,password,email,tell)Values('"
			 * +member.getUserId() +"','"+member.getPassword() +"','"+member.getEmail()
			 * +"','"+member.getTell() +"')";
			 */
			
			String query = "insert into tb_member(user_id,password,email,tell)Values(?,?,?,?)";

			pstm = conn.prepareStatement(query);

			pstm.setString(1, member.getUserId());
			pstm.setString(2, member.getPassword());
			pstm.setString(3, member.getEmail());
			pstm.setString(4, member.getTell());

			insert = pstm.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.IM01, e);
		} finally {
			jdt.close(pstm, conn);
		}

		return insert;

	}

	public int updateMember(Connection conn, Member member) {

		int update = 0;

		// Statement stmt = null;
		PreparedStatement pstm = null;

		try {

			String query = "update tb_member set password  =  ?  where user_id = ? ";

			pstm = conn.prepareStatement(query);

			pstm.setString(1, member.getPassword());
			pstm.setString(2, member.getUserId());

			update = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.UM01,e);
		} finally {
			jdt.close(pstm);
		}

		return update;

	}

	public int deleteMember(Connection conn, String userId) {
		int delete = 0;

		PreparedStatement pstm = null;

		try {

			String query = "update tb_member set is_leave = 1 where user_id = ?";

			pstm = conn.prepareStatement(query);

			pstm.setString(1, userId);
			// String query = "delete from tb_member where user_id = '" + userId + "'" ;

			delete = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DM01, e);
		} finally {
			jdt.close(pstm);

		}

		return delete;

	}

	public List<Member> selectMemberByRegdate(Connection conn, Date begin, Date end) {

		List<Member> memberList = new ArrayList<>();

		PreparedStatement pstm = null;
		// Statement stmt = null;
		ResultSet rset = null;

		try {

			// 3. 쿼리문 실행용 객체를 생성
			String query = "select * from tb_member where reg_date between ? and ?";

			pstm = conn.prepareStatement(query);

			pstm.setDate(1, begin);
			pstm.setDate(2, end);
			// 4. 쿼리 작성

			// 4. 쿼리 실행
			rset = pstm.executeQuery();

			// 5. 쿼리를 실행하고 결과를 받음
			// 한명이 아니기때문에 while문으로 받아줘야한다
			while (rset.next()) {
				Member member = new Member();
				member.setUserId(rset.getNString("user_id"));
				member.setGrade(rset.getNString("grade"));
				member.setEmail(rset.getNString("email"));
				member.setTell(rset.getNString("tell"));
				member.setPassword(rset.getNString("password"));
				member.setRegDate(rset.getDate("reg_date"));
				member.setIsLeave(rset.getInt("is_leave"));
				// 멤버를 받아올때마다 List에 넣기
				memberList.add(member);
			}

		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.SM01, e);
		} finally {
			jdt.close(rset, pstm);
		}

		return memberList;

	}

	public int deleteMember2(Connection conn, String userId) {
		int res = 0;
		PreparedStatement pstm = null;

		
		try {
			String query = "delete from tb_member where user_id = ?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1,userId);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DM01, e);
		}finally {
			jdt.close(pstm);
		}
	
		
		return res;

	}

}
