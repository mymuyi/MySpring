package org.litespring.beans.factory.config;

public interface SingletonBeanRegistery {

	void registerSingleton(String beanID, Object singletonObject);
	
	Object getSingleton(String beanID);
}
