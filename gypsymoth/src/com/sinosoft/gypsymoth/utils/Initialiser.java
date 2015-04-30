package com.sinosoft.gypsymoth.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

/**
 * 初始化
 * @author lixin
 *
 */
public class Initialiser implements ServletContextAware, ApplicationContextAware {
    
    private static final Logger _logger = Logger.getLogger(Initialiser.class);
    
    private ApplicationContext context;
    
    private String propertyFilePath;
    
    private CommonTool common;

    public Initialiser() {

    }
    
    /**
     * init method will be invoked by spring...
     */
    public void init() {
    	_logger.info("Interaction start init...");
    	Gypsymoth.setContext(context);
    	Gypsymoth.setPropertyFilePath(propertyFilePath);
    	Gypsymoth.setCommon(common);
    	_logger.info("init end...");
    }
    
    public void setPropertyFilePath(String propertyFilePath) {

        this.propertyFilePath = propertyFilePath;
    }
    
    public void setApplicationContext(ApplicationContext context) throws BeansException {

        this.context = context;
    }
    
    
    public void setServletContext(ServletContext servletContext) {

    	Gypsymoth.servletContext = servletContext;
    }
    
    /**
     * 
     */
    public static class Gypsymoth {
        
        //		private final static CallContextHolder ccHolder = new CallContextHolder();
        private static ApplicationContext context;
        
        private static ServletContext servletContext;
        
        private static Properties props = new Properties();
        
        private static CommonTool common;
        
        public static CommonTool getCommon() {
			return common;
		}

		public static void setCommon(CommonTool common) {
			Gypsymoth.common = common;
		}

		public static ApplicationContext getContext() {

            return context;
        }
        
        private static void setContext(ApplicationContext context) {

        	Gypsymoth.context = context;
        }
        
        public static ApplicationContext getApplicationContext() {

            return Gypsymoth.context;
        }
        
        private static void setPropertyFilePath(String propertyFilePath) {

            InputStream is;
            try {
                _logger.info("try to load properties from " + propertyFilePath);
                is = Gypsymoth.class.getClassLoader().getResourceAsStream(propertyFilePath);
                props.load(is);
                
            } catch (FileNotFoundException e) {
                _logger.error("Can't find properties file " + propertyFilePath, e);
            } catch (Exception e) {
                _logger.error("Can't find properties file " + propertyFilePath, e);
            }
        }

        
        public static String getPropertie(String key) {

            try {
				return StringUtils.convertFromISO8859ToUTF8(getPropertie(key, ""));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
        }
        
        public static boolean getBooleanPropertie(String key) {

            return getBooleanPropertie(key, false);
        }
        
        public static String getPropertie(String key, String defaultValue) {

            if (props != null && props.containsKey(key)) { return props.getProperty(key); }
            return defaultValue;
        }
        
        public static Object setPropertie(String key, String value){
        	
        	 if (props != null && props.containsKey(key)) { return props.setProperty(key,value); }
        	 return value;
        }
        
        public static boolean getBooleanPropertie(String key, boolean defaultValue) {

            if (props != null && props.containsKey(key)) { return Boolean.getBoolean(props.getProperty(key)); }
            return defaultValue;
        }
        
        public static int getIntegerProperty(String key, int defaultValue) {

            if (props != null && props.contains(key)) { return Integer.parseInt(props.getProperty(key)); }
            return defaultValue;
        }
        
        public static ServletContext getServletContext() {

            return servletContext;
        }
    }

	public CommonTool getCommon() {
		return common;
	}

	public void setCommon(CommonTool common) {
		this.common = common;
	}   

}