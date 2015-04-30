package com.sinosoft.gypsymoth.exception;

import org.apache.log4j.Logger;

public class AppException extends Exception{
	private final Logger _logger = Logger.getLogger(AppException.class);
    /**
     * 异常抛出
     */
    public AppException() {
        super();
    }

    /**
     * @param arg0
     */
    public AppException(String arg0) {
        super(arg0);
        _logger.error(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public AppException(String arg0, Throwable arg1) {
        super(arg0, arg1);
        _logger.error(arg0+":"+arg1.getMessage());
    }

    /**
     * @param arg0
     */
    public AppException(Throwable arg0) {
        super(arg0);
        _logger.error(arg0.getMessage());
    }

}
