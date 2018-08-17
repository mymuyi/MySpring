package org.litespring.beans;


/**
 * 自定义异常
 * @author 木易
 *
 */
public class BeansException extends RuntimeException {

	public BeansException(String message, Throwable cause) {
		super(message, cause);
	}

	public BeansException(String message) {
		super(message);
	}

}
