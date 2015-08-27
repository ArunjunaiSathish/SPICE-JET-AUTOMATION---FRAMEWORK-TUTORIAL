package com.sj.config;
import org.apache.log4j.*;
public class Log {
 static Logger logger=Logger.getLogger(Log.class.getName());
public void info(String information){
	logger.info(information);
}
public void warn(String information){
	logger.warn(information);
}
public void debug(String information){
	logger.debug(information);
}
public void error(String information){
	logger.error(information);
}
public void fatal(String information){
	logger.fatal(information);
}
}
