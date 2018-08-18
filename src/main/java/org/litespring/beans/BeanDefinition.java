package org.litespring.beans;

import java.util.List;

/**
 * 保存 bean 的定义信息，不包含 bean 实例
 * @author 木易
 *
 */
public interface BeanDefinition {
	
	public static final String SCOPE_SINGLETON = "singleton";
	public static final String SCOPE_PROTOTYPE = "prototype";
	public static final String SCOPE_DEFAULT = "";

	String getBeanClassName();

	boolean isSingleton();

	boolean isPrototype();

	String getScope();
	
	void setScope(String scope);

	List<PropertyValue> getPropertyValues();

	ConstructorArgument getConstructorArgument();

	String getID();

	boolean hasConstructorArgumentValues();

}
