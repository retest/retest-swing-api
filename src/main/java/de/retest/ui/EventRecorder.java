package de.retest.ui;

import java.awt.Component;
import java.lang.reflect.Method;
import java.util.Map;

public class EventRecorder {

	private final Method addCustomAction;

	public EventRecorder() {
		addCustomAction = getAddCustomActionMethod();
	}

	public void addAction( Action action ) {
		try {
			addCustomAction.invoke(action.getClass().getName(), action.getActionData());
		} catch (Exception e) {
			System.err.println("No real event recorder found to delegate to. Is retest JavaAgent in place?");
			e.printStackTrace();
			throw new RuntimeException("No real event recorder found to delegate to. Is retest JavaAgent in place?", e);
		}
	}

	private Method getAddCustomActionMethod() {
		try {
			Object realEventRecorder = Component.class.getMethod("getEventRecorder", new Class<?>[] {}).invoke(null, new Object[] {});
			Method addCustomAction = realEventRecorder.getClass().getMethod("addCustomAction", new Class<?>[] { String.class, Map.class });
			return addCustomAction;
		} catch (Exception e) {
			System.err.println("No real event recorder found to delegate to. Is retest JavaAgent in place?");
			e.printStackTrace();
			throw new RuntimeException("No real event recorder found to delegate to. Is retest JavaAgent in place?", e);
		}
	}

}
