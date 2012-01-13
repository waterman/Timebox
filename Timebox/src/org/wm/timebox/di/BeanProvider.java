package org.wm.timebox.di;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class BeanProvider implements BeanFactoryAware {

	private static BeanFactory factory;

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String aName) {
		T tempBean = (T) factory.getBean(aName);
		return tempBean;
	}

	@Override
	public void setBeanFactory(BeanFactory aFactory) throws BeansException {
		factory = aFactory;
	}

	public void destroy() {
		factory = null;
	}
}
