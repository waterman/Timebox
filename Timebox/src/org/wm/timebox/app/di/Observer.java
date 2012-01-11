package org.wm.timebox.app.di;

/**
 * Observer for a certain event.
 * 
 * @author Andi
 * 
 * @param <T>
 */
public interface Observer<T> {
	void notify(T anEvent);
}