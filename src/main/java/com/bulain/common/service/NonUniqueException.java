package com.bulain.common.service;

public class NonUniqueException extends ServiceException{
	private static final long serialVersionUID = -1984900547145879692L;

	public NonUniqueException() {
		super();
	}

	public NonUniqueException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonUniqueException(String message) {
		super(message);
	}

	public NonUniqueException(Throwable cause) {
		super(cause);
	}
}
