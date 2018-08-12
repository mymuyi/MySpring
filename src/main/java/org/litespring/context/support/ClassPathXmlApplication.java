package org.litespring.context.support;

import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;

public class ClassPathXmlApplication implements ApplicationContext {

	private DefaultBeanFactory factory = null;

	public ClassPathXmlApplication(String configFile) {
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(configFile);
		;
	}
	
	@Override
	public Object getBean(String beanID) {
		return factory.getBean(beanID);
	}

}
