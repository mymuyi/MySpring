package org.litespring.beans.factory.support;

import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;

public class BeanDefinitionValueResolver {

	private DefaultBeanFactory factory;

	public BeanDefinitionValueResolver(DefaultBeanFactory factory) {
		this.factory = factory;
	}

	public Object resolveValueIfNecessary(Object value) {
		if (value instanceof RuntimeBeanReference) {
			RuntimeBeanReference ref = (RuntimeBeanReference) value;
			String refID = ref.getBeanID();
			Object bean = this.factory.getBean(refID);
			return bean;
		} else if (value instanceof TypedStringValue) {
			return ((TypedStringValue) value).getValue();
		} else {
			// TODO
			throw new RuntimeException("the value " + value + " has not implemented");
		}
	}

}
