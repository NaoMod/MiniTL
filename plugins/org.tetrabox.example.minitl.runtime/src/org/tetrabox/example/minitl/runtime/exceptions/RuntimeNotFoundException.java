package org.tetrabox.example.minitl.runtime.exceptions;

/**
 * Thrown when a runtime cannot be found for a source file.
 */
public class RuntimeNotFoundException extends Exception {
	private final String sourceFile;

	public RuntimeNotFoundException(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	
	@Override
	public String getMessage() {
		return "No runtime for file \'" + sourceFile + "\'.";
	}
}
