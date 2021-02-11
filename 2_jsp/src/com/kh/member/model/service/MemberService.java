package com.kh.member.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.kh.common.exception.DataAccessException;
import com.kh.common.exception.ToAlertException;
import com.kh.common.jdbc.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

//Service
//웹어플리케이션의 비니지스 로직을 작성
//비지니스 로직에 필요한 데이터를 Controller에게 전달받고 
//추가적으로 필요한 데이터를 Dao에게 요청하여 핵심 로직인 비지니스 로직을 작성한다
//비지니스로직을 service가 담당하기 때문에 transaction관리도 Service가 담당하게 된다.
public class MemberService {

	MemberDao memberDao = new MemberDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();

	public Member memberAuthenticate(String userId, String password) {
		// Connection 생성
		Connection conn = jdt.getConnection();
		// Dao에게 Connection 주입
		// Dao는 주입받은 Connection객체로 Statement 객체를 만들어 쿼리를 실행하게 되고
		// 따라서 Service에 해당 쿼리를 commit, rollback처리할 수 있게 된다
		Member res = memberDao.memberAuthenticate(conn, userId, password);
		// 쿼리 수행이 끝났으면 Connection을 시스템자원에 반환
		jdt.close(conn);
		return res;
	}

	public Member selectMemberByld(String userId) {
		Connection conn = jdt.getConnection();
		Member res = memberDao.selectMemberByld(conn, userId);
		jdt.close(conn);
		return res;

	}

	public List<Member> selectMemberByRegdate(Date begin, Date end) {
		Connection conn = jdt.getConnection();
		List<Member> memberList = memberDao.selectMemberByRegdate(conn, begin, end);
		jdt.close(conn);
		return memberList;

	}

	public ArrayList<Member> selectMemberList() {
		Connection conn = jdt.getConnection();
		ArrayList<Member> memberList = memberDao.selectMemberList(conn);
		jdt.close(conn);
		return memberList;
	}

	public int insertMember(Member member) {
		Connection conn = jdt.getConnection();
		int res = 0;

		try {
			res = memberDao.insertMember(conn, member);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException("회원가입의 실패하였습니다");
		} finally {
			jdt.close(conn);
		}
		return res;
	}

	public int updateMember(Member member) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = memberDao.updateMember(conn, member);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		} finally {
			jdt.close(conn);
		}
		return res;

	}

	public int deleteMember(String userId) {
		Connection conn = jdt.getConnection();
		int res = 0;
		try {
			res = memberDao.deleteMember(conn, userId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
		} finally {
			jdt.close(conn);
		}
		return res;

	}

	public int deleteMember2(String userId) {
		Connection conn = jdt.getConnection();
		int res = 0;

		try {
			res = memberDao.deleteMember2(conn, userId);
			jdt.commit(conn);
		} catch (Exception e) {
			jdt.rollback(conn);
		} finally {
			jdt.close(conn);
		}
		return res;

	}

}
