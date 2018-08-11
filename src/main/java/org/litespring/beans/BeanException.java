package org.litespring.beans;

public class BeanException extends RuntimeException {

	public BeanException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeanException(String message) {
		super(message);
	}

}
