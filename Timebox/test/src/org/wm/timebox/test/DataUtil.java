package org.wm.timebox.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public class DataUtil {

	public static <T> T getAny(EntityManager aManager, Class<T> aClass, boolean aFailOnNullFlag) {
		CriteriaQuery<T> tempCriteriaQuery = aManager.getCriteriaBuilder().createQuery(aClass);
		tempCriteriaQuery.from(aClass);
		TypedQuery<T> tempQuery = aManager.createQuery(tempCriteriaQuery);
		tempQuery.setMaxResults(1);
		List<T> tempResult = tempQuery.getResultList();
		if (tempResult.isEmpty() && aFailOnNullFlag) {
			throw new RuntimeException("No instance of " + aClass.getName() + " found.");
		}
		return tempResult.isEmpty() ? null : tempResult.get(0);
	}
}
