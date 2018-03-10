package de.retest.ui;

import java.util.Map;

public interface ComponentPeer {

	Map<String, String> getIdentifyingAttributes();

	Map<String, String> getAttributes();

	// TODO How to implement "getPossibleActions" over classloader boundaries?
}
