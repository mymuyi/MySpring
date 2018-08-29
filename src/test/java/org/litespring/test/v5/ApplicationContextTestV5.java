package org.litespring.test.v5;

import java.util.List;

import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v5.PetStoreService;
import org.litespring.util.MessageTracker;

import junit.framework.Assert;

public class ApplicationContextTestV5 {

	@Test
	public void testPlaceOrder() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v5.xml");
		PetStoreService petStore = (PetStoreService) ctx.getBean("petStore");

		Assert.assertNotNull(petStore.getAccountDao());
		Assert.assertNotNull(petStore.getItemDao());

		petStore.placeOrder();

		List<String> msgs = MessageTracker.getMsgs();

		Assert.assertEquals(3, msgs.size());
		Assert.assertEquals("start tx", msgs.get(0));
		Assert.assertEquals("place order", msgs.get(1));
		Assert.assertEquals("commit tx", msgs.get(2));
	}

}
