package io.renren.common.utils.log;

import org.slf4j.LoggerFactory;

public class AppLoggerFactory {

	public static AppLogger getLogger(Class<?> clazz) {
		return new AppLogger(LoggerFactory.getLogger(clazz));
	}
}
