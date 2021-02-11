package com.kh.common.exception;

//Data와 ToAlert의 부모 Exception
public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 1074337714400084115L;

	public  CustomException(String errorCode) {
		super(errorCode);
		
	}
}
