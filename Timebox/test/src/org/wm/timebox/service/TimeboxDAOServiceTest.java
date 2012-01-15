package org.wm.timebox.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.wm.timebox.app.bo.Activity;
import org.wm.timebox.test.AbstractDITest;

public class TimeboxDAOServiceTest extends AbstractDITest {

	@Test
	public void testService() throws Exception {
		TimeboxDAOService tempBean = getBean(TimeboxDAOService.class);
		List<Activity> tempVOs = tempBean.getActivities(new ActivityQuery());
		Assert.assertNotNull(tempVOs);
		System.out.println(tempVOs);
	}
}
