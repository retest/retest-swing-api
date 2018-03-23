package de.retest.swing;

import java.lang.reflect.Method;
import java.util.Map;

public class SutSideEventRecorder {

	// The problem:
	// we need to call the listener methods from the retest thread
	// therefore we need the names of them and a classloader instance
	// that has access to those classes
	// actually even better: They could be added to both the SUT and the retest classloader
	// by adding the as a dependency to the agent - like the execution trace classes
	// but if the customer extends those classes ... how are we supposed to load them?

	private final Method addCustomAction;

	public SutSideEventRecorder(Object eventRecorder) {
		addCustomAction = getAddCustomActionMethod(eventRecorder);
	}

	public void addAction( Action action ) {
		try {
			addCustomAction.invoke(action.getClass().getName(), action.getActionData());
		} catch (Exception e) {
			System.err.println("Exception delegating to retest event recorder.");
			e.printStackTrace();
			throw new RuntimeException("Exception delegating to retest event recorder.", e);
		}
	}

	private Method getAddCustomActionMethod(Object eventRecorder) {
		try {
			Method addCustomAction = eventRecorder.getClass().getMethod("addCustomAction", new Class<?>[] { String.class, Map.class });
			return addCustomAction;
		} catch (Exception e) {
			System.err.println("Exception delegating to retest event recorder.");
			e.printStackTrace();
			throw new RuntimeException("Exception delegating to retest event recorder.", e);
		}
	}

}
