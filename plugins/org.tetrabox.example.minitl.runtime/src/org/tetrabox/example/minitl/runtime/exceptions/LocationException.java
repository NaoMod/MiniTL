package org.tetrabox.example.minitl.runtime.exceptions;

/**
 * Thrown when a location cannot be computed for a model element.
 */
public class LocationException extends Exception {
	private final String elementName;

	public LocationException(String elementName) {
		this.elementName = elementName;
	}
	
	@Override
	public String getMessage() {
		return "Couldn't process position of binding " + elementName + ".";
	}
}
