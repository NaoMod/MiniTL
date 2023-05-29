package org.tetrabox.example.minitl.runtime.exceptions;

public class ValueTypeException extends Exception {
	private final String type;

	public ValueTypeException(String type) {
		this.type = type;
	}
	
	@Override
	public String getMessage() {
		return "Unknwon value type " + type + ".";
	}
}
