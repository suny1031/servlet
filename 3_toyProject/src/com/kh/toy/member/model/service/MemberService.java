package com.kh.toy.member.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.toy.common.code.ConfigCode;
import com.kh.toy.common.exception.DataAccessException;
import com.kh.toy.common.exception.ToAlertException;

import com.kh.toy.common.jdbc.JDBCTemplate;
import com.kh.toy.common.mail.MailSender;
import com.kh.toy.common.util.http.HttpUtils;
import com.kh.toy.member.model.dao.MemberDao;
import com.kh.toy.member.model.vo.Member;


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

	
	public void authenticateEmail(Member member) {
		
		String subject = "회원가입을 마무리 해주세요";
		String htmlText = "<h1>회원 가입을 마무리 하기 위해 아래의 링크를 클릭하세요.</h1>";
		HttpUtils http = new HttpUtils();
		Map<String, String> headers = new HashMap<String,String>();
		
		//우리서버의 url
		String url = ConfigCode.DOMAIN+"/mail";
		
		//header 저장
		headers.put("Content-Type","application/x-www-form-urlencoded");
		
		//파라미터 저장
		Map<String, String> params = new HashMap<String, String>();
		params.put("template","temp_join");
		params.put("userId",member.getUserId());
		
		
		htmlText = http.post(url, http.urlEncodedForm(params), headers);
							//body에 넣어서 전송
		//네이버 메일이면 네이버 메일 도메인 뒤로 붙여서 우리 도메인 뒤로 붙게해줘야함
		String to  = member.getEmail();
		
		new MailSender().sendEmail(subject, htmlText, to);
		
		
	}
	
	
	public int insertMember(Member member) {
		Connection conn = jdt.getConnection();
		int res = 0;

		try {
			res = memberDao.insertMember(conn, member);
			jdt.commit(conn);

			
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			System.out.println("enum"+e.error);
			//throw new DataAccessException(e.error,e);
			throw new ToAlertException(e.error);
			//							우리의 이넘
			//서비스에서 캐치하기 때문에 다시 던져줘야함
			//이넘은 상수이기 때문에 안 내려가고 다오에서 주는 이넘만 전달해준다
			//ToAlert로 쓰는이유 ? 없음 그냥 예외클래스를 분리하려함
			//Data로 던져도 되긴함 로그가 두번뜸
			//얘를 잡아주는 얘가 없으니까 발동
			//내가 만든 DataAccessException에서 예외가 들어가있음 다오에서 넣어서
			//그걸 꺼내서 쓰는거같음
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
			throw new ToAlertException(e.error);
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
			throw new ToAlertException(e.error);
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
