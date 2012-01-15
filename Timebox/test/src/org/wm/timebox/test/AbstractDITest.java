package org.wm.timebox.test;

import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wm.timebox.di.BeanProvider;

public class AbstractDITest {

	@BeforeClass
	public static void initDI() {
		new ClassPathXmlApplicationContext(new String[] { "timebox-test.xml" });
	}

	protected <T> T getBean(String aName) {
		T tempBean = BeanProvider.getBean(aName);
		return tempBean;
	}
	
	protected <T> T getBean(Class<T> aClass){
		StringBuilder tempBuilder = new StringBuilder(aClass.getSimpleName());
		tempBuilder.setCharAt(0, Character.toLowerCase(tempBuilder.charAt(0)));
		return getBean(tempBuilder.toString());
	}
}
