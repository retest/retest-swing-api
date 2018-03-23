package de.retest.swing;

import java.awt.Component;
import java.util.Map;

public interface Action {

	// needs to implement ctor with Map<String, String>
	// to receive necessary information (e.g. which entry to select)

	void execute(Component component);

	Map<String, String> getActionData();
}
