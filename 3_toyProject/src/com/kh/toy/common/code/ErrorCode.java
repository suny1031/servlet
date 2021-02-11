package com.kh.toy.common.code;

public enum ErrorCode {

	SM01("회원정보를 조회하는 도중 에러가 발생하였습니다","/member/join.do"),
	IM01("회원정보를 입력하는 중에 에러가 발생하였습니다","/member/join.do"),
	//index외의 페이지로 가려면 지정해둔다
	UM01("회원정보를 수정  중 에러가 발생하였습니다"),
	DM01("회원정보를  삭제 중 에러가 발생하였습니다"),
	AUTH01("해당 페이지에 접근하실 수 없습니다"),
	MAIL01("메일 발송 중 에러가 발생했습니다"),
	AUTH02("이미 인증이 만료된 링크입니다"),
	API01("API통신 도중 에러가 발생하였습니다"),
	CD_404("존재하지 않는 경로입니다"),
	FILE01("파일 업로드중 예외가 발생하였습니다"),
	IB01("게시글 등록중 에러가 발생했습니다"),
	IF01("파일정보 등록중 에러가 발생했습니다"),
	AUTH03("게시글은 로그인 후 작성할 수 있습니다"),
	SB01("게시정보를 조회하는 도중 에러가 발생하였습니다"),
	SF01("파일정보를 조회하는 도중 에러가 발생하였습니다");
	private String errMsg;
	//result.jsp를 사용해 띄울 안내문구
	
	private String url = "/index.do";
	//result.jsp를 사용해 이동시킬 경로
	
	
	//index로 이동시킬 경우
	ErrorCode(String errMsg){
		this.errMsg = errMsg;
	}
	
	
	//index의 이외의 지정 페이지로 이동시킬경우
	ErrorCode(String errMsg, String url){
		this.errMsg = errMsg;
		this.url = url;
	}
	
	public String errMsg() {
		return errMsg;
	}
	
	public String url() {
		return url;
	}
}
