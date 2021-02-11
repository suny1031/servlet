package com.kh.toy.common.code;

public enum ConfigCode {
	
	
	DOMAIN("http://localhost:9090"),
	EMAIL("suny10312@naver.com"),
	UPLOAD_PATH("C:\\CODE\\05_Servlet\\resources\\upload\\");
	//업로드 해서 저장할 위치
	
	public String desc;
	
	ConfigCode(String desc){
		this.desc = desc;
	}

	public String toString() {
		return desc;
	}
	
	
	
	
	
	
	
	
	
}
