package org.litespring.beans.factory.support;

import java.util.ArrayList;
import java.util.List;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;

public class GenericBeanDefinition implements BeanDefinition {

	private String id;
	private String beanClassName;
	private Class<?> beanClass;
	private boolean singleton = true;
	private boolean prototype = false;
	private String scope = SCOPE_DEFAULT;
	private List<PropertyValue> propertyValues = new ArrayList<>();
	private ConstructorArgument constructorArgument = new ConstructorArgument();

	public GenericBeanDefinition(String id, String beanClassName) {
		this.id = id;
		this.beanClassName = beanClassName;
	}

	public GenericBeanDefinition() {
		// TODO Auto-generated constructor stub
	}

	public String getBeanClassName() {
		return this.beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}

	@Override
	public boolean isSingleton() {
		return this.singleton;
	}

	@Override
	public boolean isPrototype() {
		return this.prototype;
	}

	@Override
	public String getScope() {
		return this.scope;
	}

	@Override
	public void setScope(String scope) {
		this.scope = scope;
		this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
		this.prototype = SCOPE_PROTOTYPE.equals(scope);
	}

	@Override
	public List<PropertyValue> getPropertyValues() {
		return this.propertyValues;
	}

	@Override
	public ConstructorArgument getConstructorArgument() {
		return this.constructorArgument;
	}

	public void setID(String id) {
		this.id = id;
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public boolean hasConstructorArgumentValues() {
		return !this.constructorArgument.isEmpty();
	}

	public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException {
		String className = getBeanClassName();
		if (className == null) {
			return null;
		}
		Class<?> resolvedClass = classLoader.loadClass(className);
		this.beanClass = resolvedClass;
		return resolvedClass;
	}

	public Class<?> getBeanClass() throws IllegalStateException {
		if (this.beanClass == null) {
			throw new IllegalStateException(
					"Bean class name [" + this.getBeanClassName() + "] has not been resolved into an actual Class");
		}
		return this.beanClass;
	}

	public boolean hasBeanClass() {
		return this.beanClass != null;
	}

}
