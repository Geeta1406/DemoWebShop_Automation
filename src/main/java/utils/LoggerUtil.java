package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoggerUtil {
	
	public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

}
