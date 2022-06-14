package com.example.demo.exception;

public class AlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = 863259368L;
	private static final String MESSAGE = "예외처리";
	public AlreadyExistException() {
		super(MESSAGE);
	}

}
