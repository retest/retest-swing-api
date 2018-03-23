package de.retest.swing;

import java.awt.Component;

public interface Listener {

	// Needs to implement ctor with EventRecorder to send the recorded events to

	void addListenerTo(Component component);
}
