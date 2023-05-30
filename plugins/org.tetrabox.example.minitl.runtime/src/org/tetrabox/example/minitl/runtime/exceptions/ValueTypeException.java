package org.tetrabox.example.minitl.runtime.exceptions;

/**
 * Thrown when the type of a value is unknown.
 */
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
