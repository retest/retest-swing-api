package de.retest.swing;

import java.util.Map;
import java.util.Set;

public interface ComponentPeer {

	Map<String, String> getIdentifyingAttributes();

	Map<String, String> getAttributes();

	Set<Action> getPossibleActions();
}
