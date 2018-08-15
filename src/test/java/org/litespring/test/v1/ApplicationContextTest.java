package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplication;
import org.litespring.context.support.FileSystemXmlApplication;
import org.litespring.service.v1.PetStoreService;

public class ApplicationContextTest {

	@Test
	public void testGetBean() {

		ApplicationContext ctx = new ClassPathXmlApplication("petstore-v1.xml");

		PetStoreService petStore = (PetStoreService) ctx.getBean("petStore");

		Assert.assertNotNull(petStore);
	}

	@Test
	public void TestGetBeanFromFileSystem() {
		
		ApplicationContext ctx = new FileSystemXmlApplication("src/test/resources/petstore-v1.xml");

		PetStoreService petStore = (PetStoreService) ctx.getBean("petStore");

		Assert.assertNotNull(petStore);
	}

}
