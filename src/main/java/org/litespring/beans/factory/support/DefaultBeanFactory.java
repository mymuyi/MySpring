package org.litespring.beans.factory.support;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.util.ClassUtils;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
		implements ConfigurableBeanFactory, BeanDefinitionRegistry {

	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(64);
	private ClassLoader beanClassLoader;

	public DefaultBeanFactory() {
	}

	public BeanDefinition getBeanDefinition(String beanID) {
		return this.beanDefinitionMap.get(beanID);
	}

	public Object getBean(String beanID) {

		BeanDefinition bd = this.beanDefinitionMap.get(beanID);

		if (bd == null) {
			throw new BeanCreationException("Bean Definition does not exist");
		}

		if (bd.isSingleton()) {
			Object bean = this.getSingleton(beanID);
			if (bean == null) {
				bean = CreateBean(bd);
				this.registerSingleton(beanID, bean);
			}
			return bean;
		}

		return CreateBean(bd);
	}

	private Object CreateBean(BeanDefinition bd) {
		// 创建实例
		Object bean = instantiateBean(bd);
		// 设置属性
		populateBean(bd, bean);

		return bean;
	}

	private Object instantiateBean(BeanDefinition bd) {
		ClassLoader cl = getBeanClassLoader();
		String beanClassName = bd.getBeanClassName();

		try {
			Class<?> clz = cl.loadClass(beanClassName);
			return clz.newInstance();
		} catch (Exception e) {
			throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
		}
	}

	private void populateBean(BeanDefinition bd, Object bean) {
		List<PropertyValue> pvs = bd.getPropertyValues();
		if (pvs == null || pvs.isEmpty()) {
			return;
		}

		BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);

		SimpleTypeConverter converter = new SimpleTypeConverter(); 
		
		try {
			for (PropertyValue pv : pvs) {
				String propertyName = pv.getName();
				Object originalValue = pv.getValue();
				Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);

				BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					if (pd.getName().equals(propertyName)) {
						Object convertedValue = converter.convertIfNecessary(resolvedValue, pd.getPropertyType());
						pd.getWriteMethod().invoke(bean, convertedValue);
						break;
					}
				}

			}
		} catch (Exception ex) {
			throw new BeanCreationException("Failed to obtain BeanInfo for class [" + bd.getBeanClassName() + "]", ex);
		}
	}

	@Override
	public void registerBeanDefinition(String beanID, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanID, bd);
	}

	@Override
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
	}

	@Override
	public ClassLoader getBeanClassLoader() {
		return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
	}

}
