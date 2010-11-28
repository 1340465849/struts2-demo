package com.bulain.common.service;

public class NotFoundException extends ServiceException{
	private static final long serialVersionUID = -1716925705818801737L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
