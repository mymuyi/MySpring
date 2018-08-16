package org.litespring.beans.factory.config;

public class RuntimeBeanReference {
	
	private final String beanID;

	public RuntimeBeanReference(String beanName) {
		super();
		this.beanID = beanName;
	}

	public String getBeanID() {
		return beanID;
	}

}
