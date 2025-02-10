package com.solvd.subway.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum Config {

	// Config enum is used to manage 5 configuration keys
	DRIVER("driver", null),
	URL("url", null),
	USERNAME("username", null),
	PASSWORD("password", null),
	POOL_SIZE("poolSize", String.valueOf(1)); // default pool size value = 1 (but it's a String)

	// Config has 2 static fields:
	// - String with config properties file name
	// - Properties object -> https://www.geeksforgeeks.org/java-util-properties-class-java/
	private static final String CONFIG_FILE_NAME = "config.properties";
	private static final Properties PROPERTIES;

	// the Properties object is initialized in a static block when Config class is first loaded
	static {
		Properties config = new Properties();

		// get location of config.properties from Config's ClassLoader,
		// then read config.properties as an InputStream, and load it to PROPERTIES
		try {
			InputStream is = Config.class
				.getClassLoader()
				.getResourceAsStream(CONFIG_FILE_NAME);
			config.load(is);
			PROPERTIES = config;

		} catch (IOException e) {
			throw new RuntimeException("Unable to prepare config properties.", e);
		}
	}

	// each Config object has 2 non-static fields: a key and a default value
	private final String key;
	private String defaultValue;

	// constructor
	Config(String key, String defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}

	// method getValue for a Config object:
	// searches for the key in the Property object, and returns key's value.
	// if the value's not specified, it returns Config object's defaultValue
	public String getValue() {
		return PROPERTIES.getProperty(key, defaultValue);
	}
}