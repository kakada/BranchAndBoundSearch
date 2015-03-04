package edu.rupp.mite.weekend.BranchAndBoundSearch.config;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Setting {
	
	public static final String NEW_LINE = "newLine";
	public static final String NODE_SEPARATOR ="nodeSeparator";
	public static final String FILE = "file";
	
	private static Map<String, String> values = new HashMap<String, String>();
	
	public static void loadFromBundle(ResourceBundle bundle) {
		values.put(FILE, bundle.getString(FILE));
		values.put(NEW_LINE, bundle.containsKey(NEW_LINE) ? bundle.getString(NEW_LINE) : System.getProperty("line.separator"));
		// space is special character in .properties
		values.put(NODE_SEPARATOR, bundle.getString(Setting.NODE_SEPARATOR).replace("space", " "));
	}
	
	public static String get(String key) {
		return values.get(key);
	}
	
}
