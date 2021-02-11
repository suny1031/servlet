package com.kh.common.exception;

//예외처리가 강제되지 않는 예외클래스 생성
public class DataAccessException extends RuntimeException {

	private static final long serialVersionUID = 521587827126031031L;

	public DataAccessException(String errorCode) {
		super(errorCode);
	}

	public DataAccessException(String message, String errorCode) {
		super(errorCode);
	}

}
