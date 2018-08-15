package org.litespring.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.factory.config.SingletonBeanRegistery;
import org.litespring.util.Assert;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistery {

	private final Map<String, Object> singletonObject = new ConcurrentHashMap<>(64);

	@Override
	public void registerSingleton(String beanID, Object singletonObject) {
		
		Assert.notNull(beanID, "'beanName' must not be null");

		Object oldObject = this.singletonObject.get(beanID);

		if (oldObject != null) {
			throw new IllegalStateException("Could not register object [" + singletonObject + "] under bean name '"
					+ beanID + "': there is already object [" + oldObject + "] bound");
		}

		this.singletonObject.put(beanID, singletonObject);

	}

	@Override
	public Object getSingleton(String beanID) {
		return this.singletonObject.get(beanID);
	}

}
