package com.sinosoft.gypsymoth.pojo;

// Generated by MyEclipse Persistence Tools

import java.util.Date;

/**
 * ExamPort generated by MyEclipse Persistence Tools
 */
public class ExamPort extends AbstractExamPort implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ExamPort() {
	}

	/** full constructor */
	public ExamPort(String appId, String portName, long nationid,
			Date stopTime, long stopStatus) {
		super(appId, portName, nationid, stopTime, stopStatus);
	}

}
