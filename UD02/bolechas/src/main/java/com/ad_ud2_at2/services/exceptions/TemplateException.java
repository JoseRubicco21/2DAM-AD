package com.ad_ud2_at2.services.exceptions;

import java.util.Date;
import com.ad_ud2_at2.services.logger.Logger;
import com.ad_ud2_at2.services.logger.enums.LogLevel;

public class TemplateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionCode = "ERR: -CODE-";
	private Date exceptionDate;
	private LogLevel logLevel;
	
	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public TemplateException() {
		super();
		this.exceptionDate = new Date();
	}
	
	public TemplateException(String message) {
		super(message);
		this.exceptionDate = new Date();
		this.logLevel = LogLevel.ERROR;
	}

	public TemplateException(String message, LogLevel level){
		super(message);
		this.exceptionDate = new Date();
		this.logLevel = level;
	}

	public TemplateException(String message, Throwable cause) {
		super(message, cause);
		this.exceptionDate = new Date();
		this.logLevel = LogLevel.ERROR;
	}
	
	public void displayExceptionMessage(LogLevel logLevel) {
		String baseString = "| " + exceptionCode + " : " + this.getMessage() + " |";
		String errorHeader ="ERROR " + exceptionDate.toString() + " ".repeat(baseString.length()-(39));
		String boundaries = "*" + "-".repeat(baseString.length()-2) + "*";

		Logger.log(logLevel, String.format("%s",boundaries));
		Logger.log(logLevel, String.format("| %s |", errorHeader));
		Logger.log(logLevel, String.format("%s",boundaries));
		Logger.log(logLevel, String.format("%s", baseString));
		Logger.log(logLevel, String.format("%s",boundaries));
	}

	public void displayExceptionMessage(){
		String baseString = "| " + exceptionCode + " : " + this.getMessage() + " |";
		String errorHeader ="ERROR " + exceptionDate.toString() + " ".repeat(baseString.length()-(39));
		String boundaries = "*" + "=".repeat(baseString.length()-2) + "*";

		Logger.log(this.logLevel, String.format("%s",boundaries));
		Logger.log(this.logLevel, String.format("| %s |", errorHeader));
		Logger.log(this.logLevel, String.format("%s",boundaries));
		Logger.log(this.logLevel, String.format("%s", baseString));
		Logger.log(this.logLevel, String.format("%s",boundaries));
	}
}