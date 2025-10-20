package exceptions;

import java.util.Date;

public class TemplateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionCode = "ERR: -CODE-";
	private Date fechaExcepcion;
	
	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public TemplateException() {
		super();
		this.fechaExcepcion = new Date();
	}
	
	public TemplateException(String message) {
		super(message);
		this.fechaExcepcion = new Date();
	}

	public TemplateException(String message, Throwable cause) {
		super(message, cause);
		this.fechaExcepcion = new Date();
	}
	
	public void displayExceptionMessage() {
		String baseString = "| " + exceptionCode + " : " + this.getMessage() + " |";
		String errorHeader ="ERROR " + fechaExcepcion.toString() + "\\s".repeat(baseString.length()-(39));
		String boundaries = "*" + "-".repeat(baseString.length()-2) + "*";
		
		System.out.printf("%s%n",boundaries);
		System.out.printf("| %s |%n",errorHeader);
		System.out.printf("%s%n",boundaries);
		System.out.printf("%-128s%n",baseString);
		System.out.printf("%s%n",boundaries);
	}
}
