package org.litespring.context.support;

import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

public class ClassPathXmlApplication extends AbstractApplicationContext {

	public ClassPathXmlApplication(String configFile) {
		super(configFile);
	}

	@Override
	public Resource getResourceByPath(String path) {
		return new ClassPathResource(path, getBeanClassLoader());
	}

}
