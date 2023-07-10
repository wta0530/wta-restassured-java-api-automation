package com.demo.utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class Configuration {

	private Configuration() {
	}
	
	public static String paramsMap(String key) throws IOException {
		return load("parametersTestData.properties", key);
	}
	
	public static String applicationMap(String key) throws IOException {
		return load("application.properties", key);
	}
	
	@SuppressWarnings("unused")
	private static String load(String props, String key) throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append(StringUtils.replace(System.getProperty("user.dir"), "\\", "/"));
		builder.append("/src/test/resources/");
		builder.append(props);

		InputStream inputStream = new FileInputStream(builder.toString());
		Properties properties = new Properties();
		properties.load(inputStream);

		String value = properties.getProperty(key);
		if (StringUtils.isWhitespace(value)) {
			throw new NullPointerException("Value for " + key + " is empty in " + props);
		}
		if (StringUtils.isBlank(value)) {
			throw new NullPointerException(key + " not found in " + props);
		}

		return StringUtils.trim(value);
	}

	
}
